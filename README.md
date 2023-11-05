
# Custom Load Balancer

This is a pure Java implementation of a multithreaded Custom Load Balancer written by me from scratch which can serve 10 concurrent requests. The LB uses socket programming Java API & operates on the Round Robin algoritms by using a daemon thread that makes health checks using HTTP calls to instances on a fixed interval

## Demo

https://github.com/aryak0512/socket-programming/assets/57310005/75916e0b-bf57-42f8-8a42-6ead2c697315
## Run Locally

Clone the project

```bash
  git clone https://github.com/aryak0512/socket-programming.git
```

Go to the project directory

```bash
  cd socket-programming
```

Install JDK 11

Start the server. Right click -> Server.java -> Run as Java Application. Server is ready to accept requests on port 7200.


## Deployment

To build this project run

```bash
  java -jar webserver.jar
```

## Load tests
<!DOCTYPE html>
<html>
<body>

<h2>Concurreny Test results</h2>

<pre>This is ApacheBench, Version 2.3 &lt;$Revision: 1879490 $&gt;
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient).....done


Server Software:        
Server Hostname:        localhost
Server Port:            7200

Document Path:          /signup
Document Length:        0 bytes

Concurrency Level:      10
Time taken for tests:   0.003 seconds
Complete requests:      10
Failed requests:        0
Total transferred:      7210 bytes
HTML transferred:       0 bytes
Requests per second:    3644.31 [#/sec] (mean)
Time per request:       2.744 [ms] (mean)
Time per request:       0.274 [ms] (mean, across all concurrent requests)
Transfer rate:          2565.97 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    1   0.3      1       1
Processing:     0    1   0.2      1       1
Waiting:        0    0   0.3      0       1
Total:          1    1   0.2      1       2

Percentage of the requests served within a certain time (ms)
  50%      1
  66%      1
  75%      1
  80%      2
  90%      2
  95%      2
  98%      2
  99%      2
 100%      2 (longest request)</pre>



</body>
</html>

