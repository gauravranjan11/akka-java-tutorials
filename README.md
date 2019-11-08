# Akka Java Tutorials

The intention of this series is make people aware of various cool tools Akka has developed over the years.
<br />
Even though the documentation of Akka is quite comprehensive I feel it requires quite a lot of effort to get going and start using these tools in the application. And also when you search for any implementation codes we usually end up getting Scala pieces. Very rarely we get something in Java.
<br />
To overcome this I have started this to help people with sample akka code which they can quickly integrate in their applications and enjoy these really cool and powerfull tools.

The blog post related to these  can be found at following locations

1. [Akka Java Tutorials Part 1-AKKA Distributed Data](https://medium.com/@gauravranjan11/akka-java-tutorials-part-1-akka-distributed-data-e1de6bbe6286)
2. [Akka Java Tutorials Part 2- How to build a Scheduler using Akka Cluster Singleton & Akka Scheduler](https://medium.com/@gauravranjan11/akka-java-tutorials-part-2-how-to-build-a-scheduler-using-akka-cluster-singleton-akka-scheduler-d02e300e525c)

## Run the project
```
mvn clean package
cd ./akka-cluster/target/
java -jar akka-cluster-1.0-SNAPSHOT.jar
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
The cluster singleton is found here
```
00:10:52.550 [akka-java-tutorials-akka.actor.default-dispatcher-2] INFO akka.cluster.singleton.ClusterSingletonProxy - Singleton identified at [akka://akka-java-tutorials/user/ClusterSingletonActorManager/ClusterSingletonActorManager]
00:10:53.611 [akka-java-tutorials-akka.actor.default-dispatcher-19] INFO com.granjan.akka.tutorials.cluster.singleton.actor.ClusterSingletonActor - Scheduler ran after 20 seconds
```
Akka distributed publisher subscriber logs

```
00:26:10.559 [akka-java-tutorials-akka.actor.default-dispatcher-17] INFO com.granjan.akka.tutorials.distributed.publisher.Publisher - publishing message in akka cluster
00:26:10.560 [akka-java-tutorials-akka.actor.default-dispatcher-17] INFO com.granjan.akka.tutorials.distributed.subscriber.Subscriber - message received from publisher in akka cluster
```