FROM maven:3.9.9-amazoncorretto-21-alpine AS build
ADD . /build
RUN cd /build && mvn package --quiet -DskipTests

FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY --from=build /build/target/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-Dspring.profiles.active=docker", \
"-jar", "/app/app.jar"]