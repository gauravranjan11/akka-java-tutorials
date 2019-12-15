package com.granjan.akka.tutorials.http;

import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.*;
import akka.http.javadsl.unmarshalling.Unmarshaller;
import akka.stream.Materializer;
import akka.stream.javadsl.RestartSource;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RestCalls {

    public static Unmarshaller<HttpEntity, EmployeeResponseDO> unmarshaller;
    static Http http;
    static Materializer materializer;

    static LoggingAdapter log;

    public static void init(Materializer mat, ActorSystem actorSystem) {

        log = Logging.getLogger(actorSystem, RestCalls.class);
        materializer = mat;
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        unmarshaller = Jackson.unmarshaller(mapper, EmployeeResponseDO.class);
        http = Http.get(actorSystem);

        log.info("Demo Akka Http");
        getEmployeeDetails(1);
        postEmployeeDetails(EmployeeResponseDO.builder().name("akka-http:"+Math.random()).employee_name("akka-http").salary("40").age("32").build());
    }

    public static CompletionStage<EmployeeResponseDO> getEmployeeDetails(int id) {
        Gson gson = new Gson();
        //adding restart logic on rest calls in case of failures
        return RestartSource.onFailuresWithBackoff(
                Duration.ofSeconds(3),
                Duration.ofSeconds(15),
                0.2,
                2,
                () -> Source.fromCompletionStage(http.singleRequest(HttpRequest.create().withMethod(HttpMethods.GET)
                        .withUri("http://dummy.restapiexample.com/api/v1/employee/" + id)).thenCompose(res -> {
                    if (res.status().intValue() != 200) {
                        res.discardEntityBytes(materializer);
                        log.info("error in http status");
                        throw new RuntimeException();
                    } else {
                        return unmarshaller.unmarshal(res.entity().withContentType(ContentTypes.APPLICATION_JSON), materializer).<EmployeeResponseDO>thenApply(employee -> {
                            return employee;
                        });
                    }
                }).exceptionally(ex -> {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                })).mapAsync(1, res -> CompletableFuture.completedFuture(res))).
                runWith(Sink.<EmployeeResponseDO,EmployeeResponseDO>fold(null, (total, next) -> {
                    log.info("Akka HTTP GET" + " employee id:" + next.id + " employee name:" + next.employee_name + " employee age:" + next.employee_age);
                    return next;
                }), materializer);

    }

    public static CompletionStage<EmployeeResponseDO> postEmployeeDetails(EmployeeResponseDO employeeResponseDO) {
        Gson gson = new Gson();
        return http.singleRequest(HttpRequest.create().withMethod(HttpMethods.POST)
                .withEntity(HttpEntities.create(ContentTypes.APPLICATION_JSON, gson.toJson(employeeResponseDO)))
                .withUri("http://dummy.restapiexample.com/api/v1/create")).thenCompose(res -> {
            if (res.status().intValue() != 200) {
                res.discardEntityBytes(materializer);
                log.info("error in http status");
                throw new RuntimeException();
            } else {
                return unmarshaller.unmarshal(res.entity().withContentType(ContentTypes.APPLICATION_JSON), materializer).<EmployeeResponseDO>thenApply(employee -> {
                    log.info("Akka HTTP POST" + " employee id:" + employee.id + " employee name:" + employee.name);
                    return employee;
                });
            }
        }).exceptionally(ex -> {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        });
    }
}
