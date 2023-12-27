FROM openjdk:17

# Then I assign an argument where the jar file existes
ARG JAR_FILE=target/*.jar

# The the first argument is my source which will be coppied and then we define our destination which is our image serviceregistry.jar
COPY ${JAR_FILE} orderservice.jar

# CMD will be given when the images build but the ENTRYPOINT will start the container also
# So my service registry jar will be called when the container starts
ENTRYPOINT ["java", "-jar", "/orderservice.jar"]

# When my container started, I want to expose that port as well, like which port I want to expose
EXPOSE 8082