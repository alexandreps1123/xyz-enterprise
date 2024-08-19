# Etapa 1: Build da aplicação
FROM maven:3.9.2-eclipse-temurin-17 as builder

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copie o código-fonte para o container
COPY src ./src

# Compile o projeto e crie o arquivo JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:17-jdk-jammy

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o JAR da etapa anterior para a imagem final
COPY --from=builder /app/target/*.jar app.jar

# Exponha a porta que a aplicação Spring Boot vai usar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
