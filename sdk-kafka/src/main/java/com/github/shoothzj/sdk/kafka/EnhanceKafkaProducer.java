package com.github.shoothzj.sdk.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author hezhangjian
 */
@Slf4j
public class EnhanceKafkaProducer<K, V> {

    private volatile KafkaProducer<K, V> producer;

    public EnhanceKafkaProducer(Properties properties, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        this.producer = new KafkaProducer<K, V>(properties, keySerializer, valueSerializer);
    }

    /**
     * the caller should ensure thread safe call to this obj
     *
     * @param properties
     * @param keySerializer
     * @param valueSerializer
     */
    public void changeParam(Properties properties, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        KafkaProducer<K, V> oldProducer = this.producer;
        this.producer = null;
        try {
            oldProducer.close();
        } catch (Exception e) {
            log.error("ignore the old client close error");
        }
        this.producer = new KafkaProducer<K, V>(properties, keySerializer, valueSerializer);
    }

    public Future<RecordMetadata> send(ProducerRecord<K, V> record) {
        if (producer == null) {
            throw new IllegalStateException("kafka producer is switching");
        }
        return producer.send(record);
    }

}
