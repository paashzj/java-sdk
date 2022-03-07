package com.github.shoothzj.test.kafka;

import com.github.shoothzj.javatool.util.CommonUtil;
import com.github.shoothzj.test.base.LogTestExtension;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.Uuid;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@ExtendWith(LogTestExtension.class)
public class KfkProduceConsumeTest {

    private static final Logger log = LoggerFactory.getLogger(KfkProduceConsumeTest.class);

    private static TestKfkServer server;

    @BeforeAll
    public static void beforeAll() throws Exception {
        server = new TestKfkServer();
        server.start();
        CommonUtil.sleep(TimeUnit.SECONDS, 5);
    }

    @Test
    @Timeout(value = 30)
    public void testKfkProduceConsume() throws Exception {
        String topic = UUID.randomUUID().toString();
        String groupId = UUID.randomUUID().toString();
        String kafkaServers = String.format("localhost:%d", server.getKafkaPort());
        log.info("kafka servers is {}", kafkaServers);
        Properties adminProp = new Properties();
        adminProp.put("bootstrap.servers", kafkaServers);
        Admin admin = Admin.create(adminProp);
        List<NewTopic> newTopics = Collections.singletonList(new NewTopic(topic, 1, (short) 1));
        CreateTopicsResult createTopicsResult = admin.createTopics(newTopics);
        Uuid uuid = createTopicsResult.topicId(topic).get();
        log.info("create topic result is {}", uuid);
        Properties consumerProp = new Properties();
        consumerProp.put("bootstrap.servers", kafkaServers);
        consumerProp.put("auto.offset.reset", "earliest");
        consumerProp.put("group.id", groupId);
        consumerProp.put("key.deserializer", StringDeserializer.class.getName());
        consumerProp.put("value.deserializer", StringDeserializer.class.getName());
        Properties producerProp = new Properties();
        producerProp.put("bootstrap.servers", kafkaServers);
        producerProp.put("key.serializer", StringSerializer.class.getName());
        producerProp.put("value.serializer", StringSerializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProp);
        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProp);
        consumer.subscribe(Collections.singletonList(topic));
        producer.send(new ProducerRecord<>(topic, "test-msg")).get();
        CommonUtil.sleep(TimeUnit.SECONDS, 5);
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            if (consumerRecords.count() == 0) {
                continue;
            }
            Assertions.assertEquals(1, consumerRecords.count());
            ConsumerRecord<String, String> record = consumerRecords.iterator().next();
            Assertions.assertEquals("test-msg", record.value());
            producer.close();
            consumer.close();
            break;
        }
    }

    @AfterAll
    public static void afterAll() throws Exception {
        server.close();
    }

}
