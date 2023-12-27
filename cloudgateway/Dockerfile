# What base and versin is going to be the first layer
FROM openjdk:17

# Then I assign an argument where the jar file existes
ARG JAR_FILE=target/*.jar

# The the first argument is my source which will be coppied and then we define our destination which is our image serviceregistry.jar
COPY ${JAR_FILE} cloudgateway.jar

# CMD will be given when the images build but the ENTRYPOINT will start the container also
# So my service registry jar will be called when the container starts
ENTRYPOINT ["java", "-jar", "/cloudgateway.jar"]

# When my container started, I want to expose that port as well, like which port I want to expose
EXPOSE 9090
# Before building the docker project we need to install maven project by typing "mvn clean install" in the directory of the project's terminal

# Now we need to build our image by typing below command in terminal
# build to build the image, -t for giving tag name, then the version of it, the "." is the directory of the image
# docker build -t microservice/serviceregistry:0.0.1 .

# Then we need to run the docker image in terminal shown as below. In application.yaml file we defined a default url
# Therefore we need to define the environement with "-e" in the command line
# docker run -d -p8761:8761 --name -e EUREKA_SERVER_ADDRESS=http://host.docker.internal:8761/eureka configserver <The ImageID or The ImageName>

# So, if you write the comand below in termianl. We can see the running container.
# docker ps