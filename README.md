**Run the Docker image**
~~~
Pull docker image: docker pull rabrar/spring-boot-jpa-swagger-app.jar
~~~

To run the prebuild image -
~~~
docker run -p 9090:8080 rabrar/spring-boot-jpa-swagger-app.jar:latest

Now hit this url from your browser: http://localhost:9090/swagger-ui.html#/
