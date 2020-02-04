package com.product.service.streaming;

import com.product.service.AppMain;
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
    private static final Logger LOG = LoggerFactory.getLogger(AppMain.class);

    public void startProductConsumer(String topic, String kafkaUrl) {
        ProductConsumerCreator prop = new ProductConsumerCreator();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop.starter(kafkaUrl));
        consumer.subscribe(Collections.singletonList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            if (!records.isEmpty()) {
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("************************" + record.value());
                }
            }
        }
    }

    public static void main(String[] args) {
        ProductConsumer productConsumer = new ProductConsumer();
        productConsumer.startProductConsumer("event-topic", "localhost:9092");
    }
}
