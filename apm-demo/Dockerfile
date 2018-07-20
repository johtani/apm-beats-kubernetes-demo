FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} /apm-demo.jar
ARG APM_JAR_FILE
COPY ${APM_JAR_FILE} /elastic-apm-agent.jar
ENV JAVA_OPTS="-javaagent:/elastic-apm-agent.jar"
ENV APM_SERVER_HOST="localhost"
ENTRYPOINT exec java $JAVA_OPTS -Delastic.apm.server_url=http://$APM_SERVER_HOST:8200 -Djava.security.egd=file:/dev/./urandom -jar /apm-demo.jar