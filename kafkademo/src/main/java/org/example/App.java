package org.example;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String BROKER_LIST = "hdp1.ambari:6667";
    private static final String TOPIC = "topic-hzm";

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println( "Hello World!" );
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        //构建所需要发送的消息
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "Hello Kafka3!");
        //配置生产者客户端参数并创建KafkaProducer示例
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            //发送消息
            producer.send(record);
        }
        System.out.println( "Hello World3!" );
    }
}
