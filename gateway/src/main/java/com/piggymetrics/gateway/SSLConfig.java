// package com.piggymetrics.gateway;

// import javax.annotation.PostConstruct;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.Resource;
// import org.springframework.beans.factory.annotation.Value;

// @Configuration
// public class SSLConfig {

//     @Value("classpath*:truststore.jks")
//     Resource store;

//     @PostConstruct
//     private void configureSSL() {
//       //set to TLSv1.1 or TLSv1.2
//       System.setProperty("https.protocols", "TLSv1.1");
//       String storePath = null;

//       try {
//           storePath = store.getFile().getAbsolutePath();
//       } catch (Exception e) {
//           System.out.println(e);
//       }

//       //load the 'javax.net.ssl.trustStore' and
//       //'javax.net.ssl.trustStorePassword' from application.properties
//       System.setProperty("javax.net.ssl.trustStore", storePath);
//       System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
//       System.setProperty("javax.net.ssl.trustStoreType", "jks");
//       System.setProperty("javax.net.debug", "ssl");
//     }
// }