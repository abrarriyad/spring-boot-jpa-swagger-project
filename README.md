
**Build the Docker image**

To build a docker image with the package, that has been generated at the previous step following command is necessary from command line.
~~~
docker build -t spring-boot-jpa-swagger-app .

**Run the Docker image**

To run the newly created image command is give.  
~~~
docker run -p 8080:8080 spring-boot-jpa-swagger-app
~~~
NB: Need to make sure, the port 8080 is free.
~~~
Pull docker image: docker pull rabrar/spring-boot-jpa-swagger-app.jar
~~~

To run the prebuild image -
~~~
docker run -p 8080:8080 rabrar/spring-boot-jpa-swagger-app.jar:latest

or, using docker-compose
```
docker-compose up -d
```
There is also another docker compose file for development purpose.

**Build/Run with docker-compose**

To, build the  docker image and run it with docker-compose simply execute this command below

```
 docker-compose -f docker-compose.yml up -d
```

It's will first build the image with the docker context, Then run the app in 8080 port

**Access the application from browser**

Assuming the application is running on local machine, if so then the URL to access the swagger defination will be - 
~~~
http://localhost:8080/swagger-ui.html#/
~~~