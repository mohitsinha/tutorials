# Spring Boot Reactive Webflux Server-Sent Events

This is a sample application that shows how to use Server-Sent Events using:
 - Spring Boot 2
 - Spring Webflux
 
 
<br/>
Please see the following pages for more details
  
  - Spring Web Reactive <br/><a>http://docs.spring.io/spring-framework/docs/5.0.0.M1/spring-framework-reference/html/web-reactive.html</a>

## Running

Run this using using the gradle wrapper included

```
./gradlew bootRun
```

This will start the application on port 8080.


## cURL Commands

You can try the following API once the server is running.

GET __/stock/transaction__

``` curl -v http://localhost:8080/stock/transaction```
