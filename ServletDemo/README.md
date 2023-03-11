# ServletDemo

This is a project for learning Java Servlet basically.

In this project, 
- a `HttpServer` handle the request from web,
  - `WebServlet` annotation to declare http uri.
- using local tomcat as request container and config it in idea
- using `Mockito` and `JUnit` to test
- using `Thread` to simulate several clients access resource concurrently

From this project, I learned what is http servlet, how to use it, and 
I know the methods handling requests in HttpServlet is thread-unsafe, 
we must ensure that it is used safely and without conflicts when handling 
concurrent requests.