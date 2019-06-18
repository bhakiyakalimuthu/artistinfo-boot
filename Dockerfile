#Without Maven
#FROM openjdk:11
#ADD executable/target/artistinfo-boot.jar artistinfo-boot.jar
#ENTRYPOINT ["java", "-jar", "/artistinfo-boot.jar"]

#With Maven
FROM maven:3.6.1-jdk-11-slim as build
COPY . .
RUN mvn install

FROM openjdk:11
WORKDIR /app
COPY --from=build executable/target/artistinfo-boot.jar /app
ENTRYPOINT ["sh", "-c"]
EXPOSE 8080
CMD ["java -jar artistinfo-boot.jar"]