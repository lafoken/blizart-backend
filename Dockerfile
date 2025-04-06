FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]