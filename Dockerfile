# Etapa de compilación
FROM maven:3.8.3-jdk-11 AS build
COPY pom.xml /app/
WORKDIR /app
RUN mvn dependency:go-offline

COPY src /app/src
RUN mvn package -DskipTestsdoc

# Etapa de ejecución
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/prices-0.0.1-SNAPSHOT.jar /app/
#Puerto en el contenedor de docker ( que no tiene porque ser el nuestro local)
EXPOSE 8080

CMD ["java", "-jar", "prices-0.0.1-SNAPSHOT.jar"]