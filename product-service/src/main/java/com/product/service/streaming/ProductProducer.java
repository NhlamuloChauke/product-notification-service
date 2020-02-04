package com.product.service.streaming;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author nhlamulo
 */
public class ProductProducer {

    KafkaProducer<String, String> producer;
    ProducerRecord<String, String> record;

    public void EventMessage(String eventMessage, String topic, String kafkaUrl) {
        ProductProducerCreator props = new ProductProducerCreator();
        producer = new KafkaProducer<String, String>(props.starter(kafkaUrl));
        record = new ProducerRecord<String, String>(topic, eventMessage);
        producer.send(record);
    }
}
