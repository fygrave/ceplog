#!/usr/bin/env python

import urllib
import time
import datetime

#url = "http://localhost:8084/CEP0000/sendevent?stream=SyslogEvent&"
url = "http://localhost:8084/CEP0000/send?stream=SyslogEvent&"

count = 0
while  1:

    event = {}
#event["date"] = datetime.datetime.now()
    event["date"] = int(time.time())
    event["origin"] = "generator"
    event["src"]="127.0.0.1"
    event["src_port"]="12"
    event["dst"] = "192.168.0.2"
    event["dst_port"] = "12"
    event["action"] = "forward"
    event["message"] = "pack"
    event["severity"] = 0
    event["priority"] = 0
    event["severityID"] = 0
    event["time"] = time.time()

    print "%s%s" %(url, urllib.urlencode(event))
    r = urllib.urlopen("%s%s" %(url, urllib.urlencode(event)))
    count = count + 1
    print count
#    time.sleep(0.01)
