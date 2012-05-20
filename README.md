CepLog
========

This is framework to peroform large scale log analysis using Esper CEP engine. 
The main purprose of this project is to research and understand capabilities of CEP
platform in application to system log management and intrusion detection.


Building the code
------------------

We use Maven system for this project. So things should be easy:

```sh

mvn package

```

Running the code
----------------

Once built is complete, you can grab the jar and run it as:

```sh

java -jar target/ceplog-1.0-SNAPSHOT.jar

```

Sending Events
--------------

You can start sending events with something like 

```sh

curl "http://localhost:8084/sendevent?stream=SyslogEvent&date=20120505121212&message='This+is+log+message'&severity=4&origin=127.0.0.1"


```
