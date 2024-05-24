FROM openjdk:17
WORKDIR /tmp
VOLUME /var/lib/openjdk
COPY target/job-service-0.0.1-SNAPSHOT.jar app5.jar
ENTRYPOINT ["java","-jar","app5.jar"]
