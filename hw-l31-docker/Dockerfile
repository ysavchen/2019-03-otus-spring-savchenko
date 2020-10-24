FROM maven:3.6.3-jdk-11-slim AS build

ARG BUILD_DIR=/home/app

RUN mkdir -p $BUILD_DIR
WORKDIR $BUILD_DIR

COPY pom.xml $BUILD_DIR/
COPY src $BUILD_DIR/src
RUN mvn package


FROM bellsoft/liberica-openjdk-alpine:11.0.9

WORKDIR /home/app
COPY --from=build /home/app/target/*.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]