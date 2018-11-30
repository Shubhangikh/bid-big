FROM java:8-jre
MAINTAINER Alexander Lukyanchikov <sqshq@sqshq.com>

ADD ./target/items-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/items-service.jar"]

EXPOSE 8010