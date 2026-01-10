# Use official JDK image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven files to cache dependencies
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
RUN mvn -B dependency:go-offline

# Copy source code
COPY src ./src

# Expose ports
EXPOSE 8080 5005

# Enable JVM remote debugging
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

# Run Spring Boot in dev mode (live reload)
CMD ["mvn", "spring-boot:run", "-Dmaven.test.skip=true"]
