CepLog
========

This is framework to peroform large scale log analysis using Esper CEP engine. 
The main purprose of this project is to research and understand capabilities of CEP
platform in application to system log management and intrusion detection.

One of the applicable uses is to detect DDOS attacks using this engine. The sample netflow server
is provided in scripts file. 


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

curl "http://localhost:8084/sendevent?stream=SyslogEvent&date=20120505121212&src=xxxx&dst=yyyyy"

there is a sample event generation script in scripts/symevents.py

An implementation of netflow server to collect netflow data and send to the Esper engine: scripts/netflowd/


```

Viewing Results
---------------

Produced events are stored in ElasticSearch index file.
