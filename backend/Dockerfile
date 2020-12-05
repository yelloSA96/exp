FROM openjdk:8-jdk-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir ./data && chown spring:spring ./data
RUN chown spring:spring /app
USER spring:spring
COPY target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
