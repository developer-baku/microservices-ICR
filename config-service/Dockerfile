FROM openjdk:17
WORKDIR /tmp
VOLUME /var/lib/openjdk
COPY target/config-service-0.0.1-SNAPSHOT.jar app2.jar
ENTRYPOINT ["java","-jar","app2.jar"]
