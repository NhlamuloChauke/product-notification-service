package com.notification.service.notificationservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    private String kafkaUrl;
    private String kafkaTopic;
    private String timeoutMS;
    private String connectionsMax;
    private String requestTimeout;

    public String getKafkaUrl() {
        return kafkaUrl;
    }

    public void setKafkaUrl(String kafkaUrl) {
        this.kafkaUrl = kafkaUrl;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public void setKafkaTopic(String kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }

    public String getTimeoutMS() {
        return timeoutMS;
    }

    public void setTimeoutMS(String timeoutMS) {
        this.timeoutMS = timeoutMS;
    }

    public String getConnectionsMax() {
        return connectionsMax;
    }

    public void setConnectionsMax(String connectionsMax) {
        this.connectionsMax = connectionsMax;
    }

    public String getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(String requestTimeout) {
        this.requestTimeout = requestTimeout;
    }
}
