#
# Build stage
#
FROM openjdk:8-jdk AS build
RUN apt-get update
RUN apt-get install -y maven
COPY src /mytheresa/src
COPY pom.xml /mytheresa
WORKDIR /mytheresa
RUN mvn clean package -DskipTests