package com.hmall.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfig {

    @Bean
    @ConditionalOnMissingBean(KeyPair.class)
    public KeyPair keyPair(JwtProperties jwtProperties) {
        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(
                jwtProperties.getLocation(),
                jwtProperties.getPassword().toCharArray()
        );
        return keyFactory.getKeyPair(
                jwtProperties.getAlias(),
                jwtProperties.getPassword().toCharArray()
        );
    }
}