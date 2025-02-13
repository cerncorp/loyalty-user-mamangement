
FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY ./target/user-management-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-Dspring.profiles.active=docker", "-jar", "user-management-0.0.1-SNAPSHOT.jar"]