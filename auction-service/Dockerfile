FROM java:8-jre

ADD ./target/auction-service.jar /app/
CMD java ${JAVA_OPTS} -Xmx200m -jar /app/auction-service.jar

EXPOSE 8001