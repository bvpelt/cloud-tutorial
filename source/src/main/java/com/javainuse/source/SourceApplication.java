package com.javainuse.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

import java.time.LocalDate;
import java.util.Date;

@EnableBinding(Source.class)
@SpringBootApplication
public class SourceApplication {

    private long count = 0;

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public MessageSource<Long> timeMessageSource() {
        return () -> MessageBuilder.withPayload(new Date().getTime()).build();
    }

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public MessageSource<MyData> myDataMessageSource() {
        return () -> MessageBuilder.withPayload(nextData()).build();
    }

    private MyData nextData() {
        MyData result = new MyData();
        this.count++;

        LocalDate now = LocalDate.now();
        result.setDestination("Jan");
        result.setMessage("This is message: " + this.count);
        result.setSource("Generator");
        result.setTimeStamp(now);

        return result;
    }


    public static void main(String[] args) {
        SpringApplication.run(SourceApplication.class, args);
    }
}