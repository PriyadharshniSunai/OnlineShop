FROM openjdk:17
WORKDIR /app
COPY target/OnlineShop-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "OnlineShop-0.0.1-SNAPSHOT.jar"]