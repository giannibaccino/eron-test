FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiamos el jar al contenedor
COPY target/eron-api-0.0.1-SNAPSHOT.jar app.jar

# Comando de ejecución
CMD ["java", "-jar", "app.jar"]