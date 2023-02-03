package com.pratik.www.employeeleave.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@PropertySource("classpath:application.properties")
public class KafkaConfig {

    @Autowired
    Environment env;

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(env.getProperty("spring.kafka.topic.name")).build();
    }
}
