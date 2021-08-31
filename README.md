## rabbitmq-consumer-ack



## FAUX - A FAIRE

Cette application teste les "Publisher Confirms", cad les acquittements de RabbitMQ au producteur des messages.

Un agent RabbitMQ doit être lancé, avec la configuration suivante :
- une queue durable "queue1"
- un échange "exchange1" de type direct
- dans l'échange "exchange1", un binding sur "queue1", avec la binding key "queue1".

Utilise Spring Boot AMQP.

Origine :
[code de Gary Russell](https://github.com/spring-projects/spring-amqp-samples/blob/main/spring-rabbit-confirms-returns/src/main/java/org/springframework/amqp/samples/confirms/SpringRabbitConfirmsReturnsApplication.java).
