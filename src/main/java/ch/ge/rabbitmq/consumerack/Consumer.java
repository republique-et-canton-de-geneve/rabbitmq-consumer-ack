package ch.ge.rabbitmq.consumerack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import static ch.ge.rabbitmq.consumerack.MessagingApplication.QUEUE;

@Component
@Slf4j
public class Consumer {

    /**
     * Loquet pour empecher que l'application ne s'arrete trop tot.
     */
    private final CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = QUEUE)
    public void receiveMessage(String message) {
        log.info("Debut de la consommation du message [{}]", message);

        log.info("Tapez \"1\" pour consommer le message sans erreur. Tapez \"2\" pour lancer une exception");
        String userChoice = new Scanner(System.in).nextLine();
        if (userChoice.equals("2")) {
            log.info("Lancement d'une exception");
            throw new IllegalStateException("Exception indiquant une erreur de traitement du message");
            // Spring RabbitMQ va envoyer un nack a RabbitMQ et donc RabbitMQ va remettre le message dans la queue
            // et le resoumettre immediatement
        } else {
            log.info("Fin de la consommation du message (reussite)");
            // Spring RabbitMQ va envoyer un ack a RabbitMQ
        }

        latch.countDown();
    }

    CountDownLatch getLatch() {
        return latch;
    }

}
