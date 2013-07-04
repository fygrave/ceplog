netflowd
========
This is a netflow server, written in node.js that collects netflow data and sends it to the CEPLOG esper engine and statsd

Running the server
------------------
```sh

npm install
```

after this, you should be able to start server as 

```sh

node netflowd.js
```

Feeding the data
----------------

You can use fprobe to collect network data.

