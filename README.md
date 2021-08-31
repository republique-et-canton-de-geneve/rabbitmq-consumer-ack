# rabbitmq-consumer-ack

Cette application teste les "Consumer AcknowledgementsPublisher",
c'est-à-dire les acquittements (ack ou nack) renvoyés par le consommateur des messages à RabbitMQ.

Utilise Spring Boot AMQP.

## Configuration

Un agent RabbitMQ doit être lancé, avec la configuration suivante :
- une queue durable "queue1"
- un échange "exchange1" de type direct
- dans l'échange "exchange1", un binding sur "queue1", avec la binding key "queue1".

## Exécution

Il suffit de lancer l'application. Ceci déclenche les opérations suivantes :
1. le `Producer` envoie un message à RabbitMQ
2. RabbitMQ soumet le message au `Consumer`
3. le `Consumer` demande à l'utilisateur s'il veut que le message soit traité sans erreur (-> ack) ou
au contraire qu'une exception soit lancée (-> nack).
