FROM openjdk:17
WORKDIR /tmp
VOLUME /var/lib/openjdk
COPY target/review-service-0.0.1-SNAPSHOT.jar app6.jar
ENTRYPOINT ["java","-jar","app6.jar"]
