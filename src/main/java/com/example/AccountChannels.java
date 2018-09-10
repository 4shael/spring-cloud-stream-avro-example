package com.example;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AccountChannels {

    @Output("account-created")
    MessageChannel accountCreatedChannel();

}
