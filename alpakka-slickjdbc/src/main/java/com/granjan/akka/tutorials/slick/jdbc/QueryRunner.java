package com.granjan.akka.tutorials.slick.jdbc;

import akka.Done;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.stream.Materializer;
import akka.stream.alpakka.slick.javadsl.Slick;
import akka.stream.alpakka.slick.javadsl.SlickRow;
import akka.stream.alpakka.slick.javadsl.SlickSession;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

public class QueryRunner {

    static SlickSession slickSession;
    static Materializer materializer;
    static LoggingAdapter log;

    public static void init(SlickSession session, Materializer mat, ActorSystem actorSystem) {

        log = Logging.getLogger(actorSystem, QueryRunner.class);

        slickSession = session;
        materializer = mat;
        runQueries();
    }

    public static CompletionStage<ArrayList<Employee>> selectEmployeeDetails() {

        return Slick.source(slickSession,
                DBQueries.selectEmployees,
                (SlickRow res) -> {
                    return mapEmployee(res);
                }).async().runWith(Sink.fold(new ArrayList<Employee>(), (total, next) -> {
            log.info("employee id: {}, employee name :{}, employee city: {}", next.get(0).id, next.get(0).name, next.get(0).city);
            total.addAll(next);
            return total;
        }), materializer).exceptionally(ex -> {
            log.error(ex, "error in selecting employees");
            throw new CompletionException(ex);
        });
    }


    public static CompletionStage<Done> updateEmployee(Employee employee) {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        return Source.from(employees)
                .runWith(
                        Slick.sink(
                                slickSession,
                                (msg) ->
                                        DBMapper.getUpdateQuery(employee.city, employee.id)),
                        materializer);
    }

    public static CompletionStage<Done> insertEmployee(Employee employee) {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        return Source.from(employees)
                .runWith(
                        Slick.sink(
                                slickSession,
                                (msg) ->
                                        DBMapper.getinsertQuery(employee.city, employee.name, employee.age, employee.id)),
                        materializer);
    }

    public static CompletionStage<Done> deleteEmployee(Employee employee) {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        return Source.from(employees)
                .runWith(
                        Slick.sink(
                                slickSession,
                                (msg) ->
                                        DBMapper.getDeleteQuery(employee.id)),
                        materializer);
    }

    static void runQueries() {
        selectEmployeeDetails().thenAccept(select -> {
            updateEmployee(Employee.builder().id(1).city("Blr").build()).thenAccept(
                    update -> {
                        log.info("updated employee id 1 city to Blr");
                        deleteEmployee(Employee.builder().id(2).build()).thenAccept(del -> {
                            log.info("delete employee id 2");
                            insertEmployee(Employee.builder().age(23).id(3).name("Avi").city("Hyd").build()).thenAccept(insert -> {
                                log.info("inserted employee id 3");
                                selectEmployeeDetails();
                            });
                        });
                    }
            );
        });

    }

    static List<Employee> mapEmployee(SlickRow slickRow) {
        List<Employee> employees = new ArrayList<>();
        employees.add(Employee.builder().id(slickRow.nextInt()).age(slickRow.nextInt()).
                name(slickRow.nextString()).city(slickRow.nextString()).build());
        return employees;
    }
}
