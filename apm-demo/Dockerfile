FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/apm-demo.jar apm-demo.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /apm0-demo.jar