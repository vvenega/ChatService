package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer{
	
	private static final String VALID_CUSTOMER = "http://192.168.1.67:4200";
	//private static final String VALID_CUSTOMER = "*";
	
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	System.err.println("Registering StompEndPoints...");
        registry.addEndpoint("/socket")
                .setAllowedOrigins(VALID_CUSTOMER)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	System.err.println("Setting Prefixes...");
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/");
    }
}
