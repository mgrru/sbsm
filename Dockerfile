FROM maven:3.9.8-eclipse-temurin-21 AS builder
COPY . /sbsm

RUN mvn clean package

FROM eclipse-temurin:21

WORKDIR /sbsm

COPY --from=builder /sbsm/target/sbsm-0.0.1-SNAPSHOT.jar /sbsm/sbsm-0.0.1-SNAPSHOT.jar

EXPOSE 9901

CMD ["java", "-jar", "sbsm-0.0.1-SNAPSHOT.jar"]