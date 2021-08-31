package ch.ge.rabbitmq.consumerack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingApplication {

    static final String EXCHANGE = "exchange1";

    static final String ROUTING_KEY = "queue1";

    static final String QUEUE = ROUTING_KEY;

    public static void main(String[] args) {
        SpringApplication.run(MessagingApplication.class, args).close();
    }

}
