# Utilisation de l'image officielle Maven.
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app

# Copie du projet vers le container.
COPY pom.xml .
COPY src src

# Packager sans effectuer les tests.
RUN mvn -f pom.xml clean package -DskipTests

# Création de l'image finale.
FROM openjdk:17-alpine
WORKDIR /app

# Copy the built application from the previous stage.
COPY --from=build /app/target/*.jar app.jar

# Command to run the application.
ENTRYPOINT ["java","-jar","app.jar"]
