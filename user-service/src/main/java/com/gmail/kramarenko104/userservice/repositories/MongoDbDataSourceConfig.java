package com.gmail.kramarenko104.userservice.repositories;

import com.mongodb.ServerAddress;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
@PropertySource({"classpath:mongodb-data-source.properties"})
public class MongoDbDataSourceConfig extends AbstractReactiveMongoConfiguration {

    @Autowired
    Environment env;

    @Override
    public String getDatabaseName() {
        return env.getRequiredProperty("mongo.database");
    }

    @Override
    @Bean
    public MongoClient reactiveMongoClient() {
        ServerAddress serverAddress = new ServerAddress(env.getProperty("mongo.host"));
        return MongoClients.create(String.format("mongodb://%s", serverAddress));
    }

    @Bean
    @DependsOn("reactiveMongoClient")
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient reactiveMongoClient) {
        return new ReactiveMongoTemplate(reactiveMongoClient, getDatabaseName());
    }

}
