# rabbitmq-consumer-ack

Cette application teste les "Consumer Acknowledgements",
c'est-à-dire les acquittements (ack ou nack) renvoyés par le consommateur des messages à RabbitMQ.

Utilise Spring Boot AMQP et Java 17+.

## Construction

```
mvn clean package
```

## Configuration

Un serveur RabbitMQ doit être lancé, avec la configuration suivante :
- une queue durable "queue1"
- un échange "exchange1" de type direct
- dans l'échange "exchange1", un binding sur "queue1", avec la routing key "queue1".

### Exemple de commande de lancement d'un serveur RabbitMQ
```
podman run -it -p 5672:5672 -p 15672:15672 rabbitmq:3.13.6-management
```
Notes :
* Adapter le numéro de version de l'image rabbitmq
* Depuis l'État de Genève, préfixer le nom de l'image rabbitmq par le nom du dépôt Nexus,
  c'est-à-dire `docker-all.../rabbitmq:<VERSION>`
* Le fait de ne pas inclure l'option `-rm` dans la commande `podman run` ci-dessus permet
  d'arrêter (`podman stop`) et de relancer (`podman start`) le conteneur sans perdre la
  configuration de la queue et de l'échange

La console RabbitMQ est accessible à l'URL http://localhost:15672 (guest/guest).

## Exécution

Il suffit de lancer l'application (`MessagingApplication`), soit en ligne de commande via
```
java -jar target/rabbitmq-consumer-ack-1.0-SNAPSHOT.jar
```
soit directement depuis l'IDE (par ex. IntelliJ).

Le lancement de l'application déclenche les opérations suivantes :
1. Le `Producer` envoie un message à RabbitMQ
2. RabbitMQ soumet le message au `Consumer`
3. Le `Consumer` demande à l'utilisateur s'il veut que le message soit traité sans erreur (-> ack) ou
au contraire qu'une exception soit lancée (-> nack).

Suivre le résultat dans la console de RabbitMQ (dans la queue "queue1").
