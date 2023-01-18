FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER nmt0512
WORKDIR /opt/app
ARG JAR_FILE=target/SportShop-1.0.jar
COPY ${JAR_FILE} sport-shop-docker.jar
ENTRYPOINT ["java","-jar","sport-shop-docker.jar"]