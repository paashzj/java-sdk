package com.github.shoothzj.sdk.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;

import java.time.Duration;
import java.util.Properties;

/**
 * @author hezhangjian
 */
@Slf4j
public class EnhanceKafkaConsumer<K, V> {

    private volatile KafkaConsumer<K, V> consumer;

    public EnhanceKafkaConsumer(Properties properties, Deserializer<K> keyDeserializer, Deserializer<V> valueDeserializer) {
        this.consumer = new KafkaConsumer<K, V>(properties, keyDeserializer, valueDeserializer);
    }

    /**
     * the caller should ensure thread safe call to this obj
     *
     * @param properties
     * @param keyDeserializer
     * @param valueDeserializer
     */
    public void changeParam(Properties properties, Deserializer<K> keyDeserializer, Deserializer<V> valueDeserializer) {
        KafkaConsumer<K, V> oldConsumer = this.consumer;
        this.consumer = null;
        try {
            oldConsumer.close();
        } catch (Exception e) {
            log.error("ignore the old client close error");
        }
        this.consumer = new KafkaConsumer<>(properties, keyDeserializer, valueDeserializer);
    }

    public ConsumerRecords<K, V> poll(final Duration timeout) {
        if (consumer == null) {
            throw new IllegalStateException("kafka producer is switching");
        }
        return consumer.poll(timeout);
    }

}
