FROM eclipse-temurin:21-jdk
MAINTAINER temirzhan
COPY build/libs/game_shop-0.0.1-SNAPSHOT.jar final.jar
ENTRYPOINT ["java", "-jar", "final.jar"]