package com.notification.service.notificationservice.streaming;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * @author nhlamulo
 */
public class ProductConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(ProductConsumer.class);

    public void startProductConsumer(String topic, String kafkaUrl, String timeoutMS) {
        ProductConsumerCreator prop = new ProductConsumerCreator();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop.starter(kafkaUrl));
        consumer.subscribe(Collections.singletonList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Long.parseLong(timeoutMS));
            if (!records.isEmpty()) {
                for (ConsumerRecord<String, String> record : records) {
                    LOG.info("Event record {} =>", record.value());
                }
            }
        }
    }
}
