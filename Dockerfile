FROM openjdk:8
ADD . /template-service
WORKDIR /template-service
RUN chmod -R 777 ./ && ./mvnw clean package -DskipTests && \
apt-get update && \
apt-get -y install sudo && \
sudo apt install lsof
EXPOSE 8081
CMD ["java","-jar","target/template-service-0.0.1-SNAPSHOT.jar"]