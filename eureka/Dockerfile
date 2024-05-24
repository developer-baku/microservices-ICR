FROM openjdk:17
WORKDIR /tmp
VOLUME /var/lib/openjdk
COPY target/eureka-0.0.1-SNAPSHOT.jar app3.jar
ENTRYPOINT ["java","-jar","app3.jar"]
