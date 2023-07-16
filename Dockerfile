FROM jimschubert/8-jdk-alpine-mvn
ARG JAR_FILE=target/SportShop-1.0.jar
COPY ${JAR_FILE} sport-shop-docker-1.0.jar
ENTRYPOINT ["java","-jar","/sport-shop-docker-1.0.jar"]