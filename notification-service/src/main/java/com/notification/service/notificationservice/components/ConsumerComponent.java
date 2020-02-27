package com.notification.service.notificationservice.components;

import com.notification.service.notificationservice.config.ApplicationProperties;
import com.notification.service.notificationservice.streaming.ProductConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConsumerComponent {
    private static final Logger LOG = LoggerFactory.getLogger(BootstrapComponent.class);

    @Autowired
    ApplicationProperties applicationProperties;

    public String topic;
    public String kafkaUrl;
    public String timeoutMS;

    @PostConstruct
    public void init() {
        LOG.info("Starting Notification Streaming...");

        topic = applicationProperties.getKafkaTopic();
        kafkaUrl = applicationProperties.getKafkaUrl();
        timeoutMS = applicationProperties.getTimeoutMS();

        ProductConsumer productConsumer = new ProductConsumer();

        productConsumer.startProductConsumer(topic, kafkaUrl, timeoutMS);
    }
}
