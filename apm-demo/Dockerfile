FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} /apm-demo.jar
ARG APM_JAR_FILE
COPY ${APM_JAR_FILE} /elastic-apm-agent.jar
ENV JAVA_OPTS="-javaagent:/elastic-apm-agent.jar"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /apm-demo.jar