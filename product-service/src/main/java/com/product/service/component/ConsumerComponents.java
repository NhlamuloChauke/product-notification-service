package com.product.service.component;

import com.product.service.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConsumerComponents {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerComponents.class);

    @Autowired
    ApplicationProperties properties;

    @PostConstruct
    public void init() {
/*        LOG.info("start consumer component *****************");
        StreamConsumer streamConsumer = new StreamConsumer();
        LOG.info("1 consumer component *****************");
        try {
            LOG.info("2 consumer component *****************");
            streamConsumer.createStream("event-topic");
        }
        catch(Exception e){
            LOG.info("2 consumer component ***************** {}", e.getMessage());
        }*/

    }
}
