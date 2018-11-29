FROM java:8-jre
MAINTAINER Alexander Lukyanchikov <sqshq@sqshq.com>

ADD ./target/auction-service.jar /app/
CMD java ${JAVA_OPTS} -Xmx200m -jar /app/auction-service.jar

EXPOSE 8001