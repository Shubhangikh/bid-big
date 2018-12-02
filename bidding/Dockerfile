FROM java:8-jre
MAINTAINER Alexander Lukyanchikov <sqshq@sqshq.com>

ADD ./target/bidding.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/bidding.jar"]

EXPOSE 8050