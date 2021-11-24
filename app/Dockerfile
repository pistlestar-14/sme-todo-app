FROM maven:3.8.3-jdk-8

WORKDIR /app
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run
