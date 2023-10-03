FROM openjdk:17


WORKDIR /app

COPY target/chat-application-1.0.0.jar /app/app.jar



EXPOSE 8081


CMD ["java", "-jar", "app.jar"]
