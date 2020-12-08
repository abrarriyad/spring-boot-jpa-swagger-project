FROM openjdk:8-jre-alpine AS copy-prebuild-package
EXPOSE 8080
ADD target/spring-boot-jpa-swagger-app.jar spring-boot-jpa-swagger-app.jar
ENTRYPOINT ["java","-jar","/spring-boot-jpa-swagger-app.jar"]


