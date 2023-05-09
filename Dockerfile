FROM eclipse-temurin:17-jdk-alpine
ADD target/project-socialnetwork-*.jar project-socialnetwork.jar
ENTRYPOINT ["java", "-jar", "/project-socialnetwork.jar"]