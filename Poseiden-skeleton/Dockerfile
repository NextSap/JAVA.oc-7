FROM maven:3.8.3-openjdk-17 AS build
COPY src /src
COPY pom.xml pom.xml
COPY target/spring-boot-skeleton-0.0.1.jar spring-boot-skeleton.jar
COPY /doc/data.sql /docker-entrypoint-initdb.d
ENTRYPOINT ["java","-jar","spring-boot-skeleton.jar"]