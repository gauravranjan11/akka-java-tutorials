# Akka Java Tutorials

The intention of this series is make people aware of various cool tools Akka has developed over the years.
<br />
Even though the documentation of Akka is quite comprehensive I feel it requires quite a lot of effort to get going and start using these tools in the application. And also when you search for any implementation codes we usually end up getting Scala pieces. Very rarely we get something in Java.
<br />
To overcome this I have started this to help people with sample akka code which they can quickly integrate in their applications and enjoy these really cool and powerfull tools.

## Run the project
```
mvn clean package
cd ./akka-cluster/target/
java -jar akka-cluster-1.0-SNAPSHOT.jar
```

You should see following output

```
java -jar akka-cluster-1.0-SNAPSHOT.jar
11:13:26.014 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
11:13:26.065 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.remote.Remoting - Starting remoting
11:13:26.269 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.remote.Remoting - Remoting started; listening on addresses :[akka.tcp://akka-java-tutorials@127.0.0.1:2500]
11:13:26.299 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - Starting up, Akka version [2.5.23] ...
11:13:26.515 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - Registered cluster JMX MBean [akka:type=Cluster]
11:13:26.516 [akka-java-tutorials-akka.actor.default-dispatcher-3] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - Started up successfully
11:13:26.585 [akka-java-tutorials-akka.actor.default-dispatcher-18] WARN akka.cluster.AutoDown - Don't use auto-down feature of Akka Cluster in production. See 'Auto-downing (DO NOT USE)' section of Akka Cluster documentation.
11:13:26.610 [akka-java-tutorials-akka.actor.default-dispatcher-18] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] is JOINING itself (with roles [akka-java-tutorials-node, dc-default]) and forming new cluster
11:13:26.614 [akka-java-tutorials-akka.actor.default-dispatcher-18] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - is the new leader among reachable nodes (more leaders may exist)
11:13:26.638 [akka-java-tutorials-akka.actor.default-dispatcher-18] INFO akka.cluster.Cluster(akka://akka-java-tutorials) - Cluster Node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] - Leader is moving node [akka.tcp://akka-java-tutorials@127.0.0.1:2500] to [Up]
11:13:26.876 [akka-java-tutorials-akka.actor.default-dispatcher-16] INFO com.akka.tutorials.crdt.CrdtActor - update-key:second update-message:secondMessage
11:13:26.876 [akka-java-tutorials-akka.actor.default-dispatcher-16] INFO com.akka.tutorials.crdt.CrdtActor - update-key:first update-message:firstMessage
11:13:26.876 [akka-java-tutorials-akka.actor.default-dispatcher-16] INFO com.akka.tutorials.crdt.CrdtActor - update-key:third update-message:thirdMessage
11:13:26.877 [akka-java-tutorials-akka.actor.default-dispatcher-16] INFO com.akka.tutorials.crdt.CrdtActor - get-key:first get-message:firstMessage
11:13:26.877 [akka-java-tutorials-akka.actor.default-dispatcher-16] INFO com.akka.tutorials.crdt.CrdtActor - update-key:fourth update-message:fourthMessage
11:13:27.350 [akka-java-tutorials-akka.actor.default-dispatcher-16] INFO com.akka.tutorials.crdt.CrdtActor - crdt-key:first crdt-message:firstMessage
11:13:27.351 [akka-java-tutorials-akka.actor.default-dispatcher-16] INFO com.akka.tutorials.crdt.CrdtActor - crdt-key:second crdt-message:secondMessage
11:13:27.351 [akka-java-tutorials-akka.actor.default-dispatcher-16] INFO com.akka.tutorials.crdt.CrdtActor - crdt-key:fourth crdt-message:fourthMessage

```
