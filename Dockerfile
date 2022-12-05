FROM openjdk:17-alpine
EXPOSE 8089
ADD build/libs/drone_delivery-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]