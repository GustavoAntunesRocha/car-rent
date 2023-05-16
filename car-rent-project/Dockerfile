FROM eclipse-temurin:20-jdk
EXPOSE 8080
ARG JAR_FILE=target/car-rent-project-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
