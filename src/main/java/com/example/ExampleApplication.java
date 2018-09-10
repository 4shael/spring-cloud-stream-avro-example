package com.example;

import com.example.avro.AccountTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@SpringBootApplication
public class ExampleApplication {

    @Autowired
    private final AccountChannels accountChannels;

    public ExampleApplication(AccountChannels accountChannels) {
        this.accountChannels = accountChannels;
    }


    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }


    @EventListener(ContextRefreshedEvent.class)
    public void onEvent(ContextRefreshedEvent e) {
        accountChannels.accountCreatedChannel().send(MessageBuilder.withPayload(
                AccountTO.newBuilder()
                        .setId(UUID.randomUUID().toString())
                        .setEmail("example@mail.com")
                        .build())
                .build());
    }
}
