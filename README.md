# Akka Java Tutorials

The intention of this series is make people aware of various cool tools Akka has developed over the years.
<br />
Even though the documentation of Akka is quite comprehensive I feel it requires quite a lot of effort to get going and start using these tools in the application. And also when you search for any implementation codes we usually end up getting Scala pieces. Very rarely we get something in Java.
<br />
To overcome this I have started this to help people with sample akka code which they can quickly integrate in their applications and enjoy these really cool and powerfull tools.

The blog post related to these  can be found at following locations

1. [Akka Java Tutorials Part 1 - AKKA Distributed Data](https://medium.com/@gauravranjan11/akka-java-tutorials-part-1-akka-distributed-data-e1de6bbe6286)
2. [Akka Java Tutorials Part 2 - How to build a Scheduler using Akka Cluster Singleton & Akka Scheduler](https://medium.com/@gauravranjan11/akka-java-tutorials-part-2-how-to-build-a-scheduler-using-akka-cluster-singleton-akka-scheduler-d02e300e525c)
3. [Akka Java Tutorials Part 3 - Distributed Publisher Subscriber  ](https://medium.com/@gauravranjan11/akka-java-tutorials-part-3-distributed-publisher-subscriber-bd941aef822d)

## Run the project
```
mvn exec:java
```

You should see following output

```
00:10:50.646 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
00:10:50.705 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.remote.Remoting - Starting remoting
00:10:50.932 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.remote.Remoting - Remoting started; listening on addresses :[akka.tcp://akka-java-tutorials@127.0.0.1:2500]
00:10:50.957 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - Starting up, Akka version [2.5.23] ...
00:10:51.200 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - Registered cluster JMX MBean [akka:type=Cluster]
00:10:51.201 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - Started up successfully
00:10:51.259 [akka-java-tutorials-akka.actor.default-dispatcher-4] WARN akka.cluster.AutoDown - Don't use auto-down feature of Akka Cluster in production. See 'Auto-downing (DO NOT USE)' section of Akka Cluster documentation.
00:10:51.292 [akka-java-tutorials-akka.actor.default-dispatcher-4] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] is JOINING itself (with roles [akka-java-tutorials-node, dc-default]) and forming new cluster
00:10:51.313 [akka-java-tutorials-akka.actor.default-dispatcher-4] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - is the new leader among reachable nodes (more leaders may exist)
00:10:51.332 [akka-java-tutorials-akka.actor.default-dispatcher-4] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - Leader is moving node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] to [Up]
00:10:51.592 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.cluster.singleton.ClusterSingletonManager - Singleton manager starting singleton actor [akka://akka-java-tutorials/user/ClusterSingletonActorManager/ClusterSingletonActorManager]
00:10:51.593 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.cluster.singleton.ClusterSingletonManager - ClusterSingletonManager state change [Start -> Oldest]
00:10:51.602 [akka-java-tutorials-akka.actor.default-dispatcher-2] INFO com.granjan.akka.tutorials.distributed.data.crdt.CrdtActor - update-key:third update-message:thirdMessage
00:10:51.603 [akka-java-tutorials-akka.actor.default-dispatcher-2] INFO com.granjan.akka.tutorials.distributed.data.crdt.CrdtActor - update-key:first update-message:firstMessage
00:10:51.603 [akka-java-tutorials-akka.actor.default-dispatcher-4] INFO com.granjan.akka.tutorials.distributed.data.crdt.CrdtActor - update-key:second update-message:secondMessage
00:10:51.604 [akka-java-tutorials-akka.actor.default-dispatcher-4] INFO com.granjan.akka.tutorials.distributed.data.crdt.CrdtActor - update-key:fourth update-message:fourthMessage
00:10:51.604 [akka-java-tutorials-akka.actor.default-dispatcher-4] INFO com.granjan.akka.tutorials.distributed.data.crdt.CrdtActor - get-key:first get-message:firstMessage
00:10:52.022 [akka-java-tutorials-akka.actor.default-dispatcher-4] INFO com.granjan.akka.tutorials.distributed.data.crdt.CrdtActor - crdt-key:first crdt-message:firstMessage
00:10:52.023 [akka-java-tutorials-akka.actor.default-dispatcher-4] INFO com.granjan.akka.tutorials.distributed.data.crdt.CrdtActor - crdt-key:second crdt-message:secondMessage
00:10:52.024 [akka-java-tutorials-akka.actor.default-dispatcher-4] INFO com.granjan.akka.tutorials.distributed.data.crdt.CrdtActor - crdt-key:fourth crdt-message:fourthMessage
00:10:52.550 [akka-java-tutorials-akka.actor.default-dispatcher-2] INFO akka.cluster.singleton.ClusterSingletonProxy - Singleton identified at [akka://akka-java-tutorials/user/ClusterSingletonActorManager/ClusterSingletonActorManager]
00:10:53.611 [akka-java-tutorials-akka.actor.default-dispatcher-19] INFO com.granjan.akka.tutorials.cluster.singleton.actor.ClusterSingletonActor - Scheduler ran after 20 seconds

```
The cluster singleton logs found here
```
00:10:52.550 [akka-java-tutorials-akka.actor.default-dispatcher-2] INFO akka.cluster.singleton.ClusterSingletonProxy - Singleton identified at [akka://akka-java-tutorials/user/ClusterSingletonActorManager/ClusterSingletonActorManager]
00:10:53.611 [akka-java-tutorials-akka.actor.default-dispatcher-19] INFO com.granjan.akka.tutorials.cluster.singleton.actor.ClusterSingletonActor - Scheduler ran after 20 seconds
```
Akka distributed publisher subscriber logs

```
00:26:10.559 [akka-java-tutorials-akka.actor.default-dispatcher-17] INFO com.granjan.akka.tutorials.distributed.publisher.Publisher - publishing message in akka cluster
00:26:10.560 [akka-java-tutorials-akka.actor.default-dispatcher-17] INFO com.granjan.akka.tutorials.distributed.subscriber.Subscriber - message received from publisher in akka cluster
```

Akka streams for JDBC queries

```
23:41:24.079 [db-1] DEBUG slick.basic.BasicBackend.stream - Starting initial streaming action, realDemand = 16
23:41:24.082 [db-1] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: select id,age,name,city from  employees
23:41:24.090 [db-1] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared statement: prep2: select id,age,name,city from  employees
23:41:24.091 [db-1] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared statement: prep2: select id,age,name,city from  employees
23:41:24.093 [db-1] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared statement took 1ms
23:41:24.111 [akka-java-tutorials-akka.actor.default-dispatcher-15] INFO com.granjan.akka.tutorials.slick.jdbc.QueryRunner - employee id: 1, employee name :gaurav, employee city: Bangalore
23:41:24.111 [akka-java-tutorials-akka.actor.default-dispatcher-15] INFO com.granjan.akka.tutorials.slick.jdbc.QueryRunner - employee id: 2, employee name :ranjan, employee city: Bangalore
23:41:24.131 [db-1] DEBUG slick.jdbc.StatementInvoker.result - /----+-----+--------+-----------\
23:41:24.131 [db-1] DEBUG slick.jdbc.StatementInvoker.result - | 1  | 2   | 3      | 4         |
23:41:24.131 [db-1] DEBUG slick.jdbc.StatementInvoker.result - | ID | AGE | NAME   | CITY      |
23:41:24.131 [db-1] DEBUG slick.jdbc.StatementInvoker.result - |----+-----+--------+-----------|
23:41:24.131 [db-1] DEBUG slick.jdbc.StatementInvoker.result - | 1  | 32  | gaurav | Bangalore |
23:41:24.131 [db-1] DEBUG slick.jdbc.StatementInvoker.result - | 2  | 32  | ranjan | Bangalore |
23:41:24.132 [db-1] DEBUG slick.jdbc.StatementInvoker.result - \----+-----+--------+-----------/
23:41:24.153 [db-1] DEBUG slick.basic.BasicBackend.stream - Signaling onComplete()
23:41:24.154 [db-1] DEBUG slick.basic.BasicBackend.stream - Sent up to 16 elements - Stream completely delivered
23:41:24.155 [db-1] DEBUG slick.basic.BasicBackend.stream - Finished streaming action
23:41:24.216 [akka-java-tutorials-akka.actor.default-dispatcher-4] DEBUG slick.basic.BasicBackend.action - #1: StreamingInvokerAction$HeadAction [update employees set city='Blr' where id=1]
23:41:24.234 [db-2] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: update employees set city='Blr' where id=1
23:41:24.235 [db-2] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared statement: prep6: update employees set city='Blr' where id=1
23:41:24.235 [db-2] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared statement: prep6: update employees set city='Blr' where id=1
23:41:24.238 [db-2] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared statement took 2ms
23:41:24.239 [db-2] DEBUG slick.jdbc.StatementInvoker.result - 1 rows affected
23:41:24.252 [akka-java-tutorials-akka.actor.default-dispatcher-15] INFO com.granjan.akka.tutorials.slick.jdbc.QueryRunner - updated employee id 1 city to Blr
23:41:24.255 [akka-java-tutorials-akka.actor.default-dispatcher-2] DEBUG slick.basic.BasicBackend.action - #1: StreamingInvokerAction$HeadAction [delete from employees where id=2]
23:41:24.272 [db-3] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: delete from employees where id=2
23:41:24.272 [db-3] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared statement: prep10: delete from employees where id=2
23:41:24.272 [db-3] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared statement: prep10: delete from employees where id=2
23:41:24.273 [db-3] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared statement took 413µs
23:41:24.273 [db-3] DEBUG slick.jdbc.StatementInvoker.result - 1 rows affected
23:41:24.283 [akka-java-tutorials-akka.actor.default-dispatcher-15] INFO com.granjan.akka.tutorials.slick.jdbc.QueryRunner - delete employee id 2
23:41:24.286 [akka-java-tutorials-akka.actor.default-dispatcher-15] DEBUG slick.basic.BasicBackend.action - #1: StreamingInvokerAction$HeadAction [insert into employees values(3,'Avi',23,'Hyd')]
23:41:24.304 [db-4] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: insert into employees values(3,'Avi',23,'Hyd')
23:41:24.305 [db-4] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared statement: prep14: insert into employees values(3,'Avi',23,'Hyd')
23:41:24.305 [db-4] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared statement: prep14: insert into employees values(3,'Avi',23,'Hyd')
23:41:24.308 [db-4] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared statement took 2ms
23:41:24.308 [db-4] DEBUG slick.jdbc.StatementInvoker.result - 1 rows affected
23:41:24.347 [akka-java-tutorials-akka.actor.default-dispatcher-15] INFO com.granjan.akka.tutorials.slick.jdbc.QueryRunner - inserted employee id 3
23:41:24.350 [akka-java-tutorials-akka.actor.default-dispatcher-15] DEBUG slick.basic.BasicBackend.stream - Signaling onSubscribe(slick.jdbc.JdbcBackend$JdbcStreamingActionContext@72cb1851)
23:41:24.350 [akka-java-tutorials-akka.actor.default-dispatcher-15] DEBUG slick.basic.BasicBackend.action - #1: StreamingResultAction [select id,age,name,city from  employees]
23:41:24.367 [db-5] DEBUG slick.basic.BasicBackend.stream - Starting initial streaming action, realDemand = 16
23:41:24.367 [db-5] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: select id,age,name,city from  employees
23:41:24.368 [db-5] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared statement: prep18: select id,age,name,city from  employees
23:41:24.368 [db-5] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared statement: prep18: select id,age,name,city from  employees
23:41:24.368 [db-5] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared statement took 140µs
23:41:24.369 [akka-java-tutorials-akka.actor.default-dispatcher-15] INFO com.granjan.akka.tutorials.slick.jdbc.QueryRunner - employee id: 1, employee name :gaurav, employee city: Blr
23:41:24.369 [akka-java-tutorials-akka.actor.default-dispatcher-15] INFO com.granjan.akka.tutorials.slick.jdbc.QueryRunner - employee id: 3, employee name :Avi, employee city: Hyd
23:41:24.369 [db-5] DEBUG slick.jdbc.StatementInvoker.result - /----+-----+--------+------\
23:41:24.369 [db-5] DEBUG slick.jdbc.StatementInvoker.result - | 1  | 2   | 3      | 4    |
23:41:24.370 [db-5] DEBUG slick.jdbc.StatementInvoker.result - | ID | AGE | NAME   | CITY |
23:41:24.370 [db-5] DEBUG slick.jdbc.StatementInvoker.result - |----+-----+--------+------|
23:41:24.370 [db-5] DEBUG slick.jdbc.StatementInvoker.result - | 1  | 32  | gaurav | Blr  |
23:41:24.370 [db-5] DEBUG slick.jdbc.StatementInvoker.result - | 3  | 23  | Avi    | Hyd  |
23:41:24.370 [db-5] DEBUG slick.jdbc.StatementInvoker.result - \----+-----+--------+------/


```