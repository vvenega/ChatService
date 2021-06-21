FROM adoptopenjdk/openjdk8
WORKDIR /
ARG ChatService-0.0.1-SNAPSHOT.jar
ADD ChatService-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8008
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]