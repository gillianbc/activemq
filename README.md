# activemq_basics

Example maven java projects for a broker, a producer and a consumer.  Followed this helpful tutorial.
https://tech.pookey.co.uk/playing-with-activemq-using-maven/

# Active MQ Standalone
Follow the Windows Binary Installation download instructions here:  https://activemq.apache.org/getting-started

Unzip e.g. to C:\Utilities

At the shell, go to C:\Utilities\apache-activemq-5.15.9-bin\apache-activemq-5.15.9\bin

```activemq start```

To stop, press Ctrl-C

## Check if it is running
```netstat -ano  | findstr 61616```

## Kill by PID
```taskkill /F /PID <PID>```
  
## Admin Console
http://localhost:8161  (This doesn't seem to work if I start activemq from the mq project)

# From Java Application
To start activemq, either start it standalone, or run the mq app like so:

```mvn org.apache.activemq.tooling:maven-activemq-plugin:5.2.0:run```

Both the standalone and mq have it configured to run on the standard port 61616.  It's configured in conf/activemq.xml

(If you try to run it when it's already running, you'll get an error).

To produce some sample messages for the test queue, run the producer:

```mvn clean compile exec:java -Dexec.mainClass=org.gillianbc.App```

Have a look in the admin console to view the messages.

To consume some sample messages, run the consumer:

```clean compile exec:java -Dexec.mainClass=org.gillianbc.App```

# Next Steps TODO 

- Produce messages in different queues
- Consume messages using a filter
- Have multiple producers, consumers
- Subscriptions (is that topics?) with multiple subscribers
- Junit test cases
- Can I check what's in a queue without consuming it?

