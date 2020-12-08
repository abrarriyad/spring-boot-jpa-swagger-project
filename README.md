
## Prerequisite

You should have following tools installed in your system to run the application

- jdk-1.8.0

- maven 3.6.3 (Optional. Requires, if you don't use mvnw/docker)

- docker (Optional. Requires, if run with docker)

- docker-compose (Optional. Requires, if run with docker-compose)


**Build the application with Maven**

It's a maven based application, To build the application following command need to be run from command line.
~~~
mvn package
~~~
or
~~~
mvnw package
~~~
Again you will need to have minimum JDK8 available at you PATH variable. If you don't have JDK8 installed please follow the docker build section 2.

**Run the application**

~~~
java -jar target/spring-boot-jpa-swagger-app.jar
~~~

It's an spring boot application. So, I can be run with spring-boot-maven plug-in like below

```
./mvnw spring-boot:run
```
**Build the Docker image**

- S1.
To build a docker image with the package, that has been generated at the previous step following command is necessary from command line.
~~~
docker build -t spring-boot-jpa-swagger-app .
~~~
- S2.

**[Multi-stage](https://docs.docker.com/develop/develop-images/multistage-build/) Docker build: (Everything using docker)**

It's a great way to ensure builds are 100% reproducible AND as lean as possible. On the downside a Maven build in Docker may have to download many dependencies each time it runs. But RUN’ing the `dependency:go-offline` goal, this will download most* of the dependencies required for the build and cache them for as long as the `pom.xml` **doesn’t change**.

At file DockerfileBuildWIthMavenImage is has been illustrated how to build the package from this project source code with maven docker image and then build the docker image. 
Its pretty helpful if no JDK is installed in the system. Only dependency is docker. Following is the command -

~~~
docker build -t spring-boot-jpa-swagger-app -f DockerfileBuildWIthMavenImage .
~~~
**Run the Docker image**
To run the newly created image command is give.  
~~~
docker run -p 8080:8080 spring-boot-jpa-swagger-app
~~~
NB: Need to make sure, the port 8080 is free.
~~~
pull docker image: docker pull rabrar/spring-boot-jpa-swagger-app.jar
~~~

To run the prebuild image -
~~~
docker run -p 8080:8080 rabrar/spring-boot-jpa-swagger-app.jar:latest
~~~
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

There is also another docker compose file for development purpose.

```
 docker-compose -f docker-compose.dev.yml up -d
```

It's will first build the image with the docker context, Then run the app in 8080 port

**Access the application from browser**

Assuming the application is running on local machine, if so then the URL to access the swagger defination will be - 
~~~
http://localhost:8080/swagger-ui.html#/
~~~
