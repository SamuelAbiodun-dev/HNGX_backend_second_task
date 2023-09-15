FROM maven:3.8.7 as build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests -Dmaven.compiler.source=17 -Dmaven.compiler.target=17

FROM eclipse-temurin:17

COPY --from=build /app/target/*.jar /app.jar

EXPOSE 8080

CMD ["java", "-jar", "-Dserver.port=8080", "app.jar"]
