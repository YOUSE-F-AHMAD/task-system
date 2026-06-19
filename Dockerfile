FROM amazoncorretto:17

WORKDIR /app

COPY target/task-system-0.0.1-SNAPSHOT.jar /app/task-system-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java","-jar","task-system-0.0.1-SNAPSHOT.jar"]