FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/WebServiceFinalProject-0.0.1-SNAPSHOT.jar /app/finalproject.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "finalproject.jar"]