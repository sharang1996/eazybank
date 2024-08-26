# Start with a base image containing the java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
LABEL authors="Sharang Gupta"

# Add the application's jar to the image
COPY target/account-*.jar account.jar

# execute the application
ENTRYPOINT ["java","-jar","account.jar"]
