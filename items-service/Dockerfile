FROM java:8-jre

ADD ./target/items-service.jar /app/
CMD java ${JAVA_OPTS} -Xmx200m -jar /app/items-service.jar

EXPOSE 8010