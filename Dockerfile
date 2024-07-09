FROM maven:3.9.8-eclipse-temurin-21
COPY . /sbsm
WORKDIR /sbsm

CMD ["mvn","spring-boot:run"]

EXPOSE 9901