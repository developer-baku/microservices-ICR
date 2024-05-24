FROM openjdk:17
WORKDIR /tmp
VOLUME /var/lib/openjdk
COPY target/gateway-0.0.1-SNAPSHOT.jar app4.jar
ENTRYPOINT ["java","-jar","app4.jar"]
