# Etapa 1: Compilar con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos pom.xml y resolvemos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Compilamos y empacamos (sin tests para más rápido)
RUN mvn clean package -DskipTests

# Etapa 2: Imagen liviana para ejecutar
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiamos el jar compilado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto (Render necesita que coincida con tu app)
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
