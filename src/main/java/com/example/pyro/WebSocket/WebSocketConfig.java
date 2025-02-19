package com.example.pyro.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Use allowedOriginPatterns instead of allowedOrigins
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // Allow all origins
                .withSockJS(); // Enable SockJS fallback options
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*"); // Allow all origins
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a simple in-memory message broker
        registry.enableSimpleBroker("/all", "/specific");
        // Set the application destination prefix
        registry.setApplicationDestinationPrefixes("/app");
    }
}