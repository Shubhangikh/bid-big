package com.piggymetrics.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthApplication extends ResourceServerConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}


	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
        http.authorizeRequests()
                .antMatchers("/users/reset**" , "/users/**").permitAll();
		// @formatter:on
   }


}
