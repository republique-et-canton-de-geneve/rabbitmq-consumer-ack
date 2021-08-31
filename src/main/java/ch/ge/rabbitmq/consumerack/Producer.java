package ch.ge.rabbitmq.consumerack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static ch.ge.rabbitmq.consumerack.MessagingApplication.EXCHANGE;
import static ch.ge.rabbitmq.consumerack.MessagingApplication.ROUTING_KEY;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
@Slf4j
public class Producer implements CommandLineRunner {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private Consumer consumer;

    @Override
    public void run(String... args) throws InterruptedException {
        // envoi du message
        log.info("Envoi du message");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, "Message 1");

        // on ne tue pas l'execution tant que le consommateur n'a pas mis le loquet (latch) a 0
        consumer.getLatch().await(40 * 1000, MILLISECONDS);
        log.info("Exit Producer");
    }

}
