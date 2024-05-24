FROM openjdk:17
WORKDIR /tmp
VOLUME /var/lib/openjdk
COPY target/company-service-0.0.1-SNAPSHOT.jar app1.jar
ENTRYPOINT ["java","-jar","app1.jar"]
