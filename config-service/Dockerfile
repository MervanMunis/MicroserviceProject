FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} cloudgateway.jar

ENTRYPOINT ["java", "-jar", "/cloudgateway.jar"]
EXPOSE 9090
# Before building the docker project we need to install maven project by typing "mvn clean install" in the directory of the project's terminal

# Now we need to build our image by typing below command in terminal
# build to build the image, -t for giving tag name, then the version of it, the "." is the directory of the image
# docker build -t microservice/cloudgateway:0.0.1 .

# Then we need to run the docker image in terminal shown as below. In application.yaml file we defined a default url
# Therefore we need to define the environement with "-e" in the command line
# docker run -d --name cloudgateway -p 9090:9090 --network eureka-server microservice/cloudgateway
# So, if you write the comand below in termianl. We can see the running container.
# docker ps