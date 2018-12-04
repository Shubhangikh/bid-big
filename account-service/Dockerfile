FROM java:8-jre

ADD ./target/account-service.jar /app/
COPY truststore.jks /app/
CMD java  -Djavax.net.ssl.trustStore=/app/truststore.jks -Djavax.net.ssl.trustStoreType=jks -Xmx200m -jar /app/account-service.jar


EXPOSE 6000