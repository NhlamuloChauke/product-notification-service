
package com.notification.service.notificationservice.components;

import com.notification.service.notificationservice.config.ApplicationProperties;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author nhlamulo
 */
@Component
public class BootstrapComponent {
    private static final Logger LOG = LoggerFactory.getLogger(BootstrapComponent.class);

    public String kafkaURL;
    public String eventTopic;
    public String connectionsMax;
    public String requestTimeout;
    public String timeoutMS;

    @Autowired
    ApplicationProperties properties;

    @PostConstruct
    public void init() {
        LOG.info("Starting Service Initialization...");
        kafkaURL = properties.getKafkaUrl();
        eventTopic = properties.getKafkaTopic();
        connectionsMax = properties.getConnectionsMax();
        requestTimeout = properties.getRequestTimeout();
        timeoutMS = properties.getTimeoutMS();

        LOG.info("Kafka URL: {}", kafkaURL);
        LOG.info("Kafka Event Topic: {}", eventTopic);
        LOG.info("Kafka Connection Max: {}", connectionsMax);
        LOG.info("Kafka Request Timeout: {}", requestTimeout);
        LOG.info("Kafka TimeoutMs: {}", timeoutMS);

        initializer();
    }

    public boolean isKafkaConnected() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaURL);
        properties.put("connections.max.idle.ms", connectionsMax);
        properties.put("request.timeout.ms", requestTimeout);

        try {
            AdminClient client = KafkaAdminClient.create(properties);
            ListTopicsResult topics = client.listTopics();
            Set<String> names = topics.names().get();
            LOG.info("Kafka is connected successfully");
            if (names.isEmpty()) {
                LOG.info("Kafka is connected, no topics found!");
            }
            return true;
        } catch (InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
            System.exit(0);
            return false;
        }
    }

    public boolean initializer() {
        if (!isKafkaConnected()) {
            return false;
        }
        return true;
    }
}

