package config;

import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaConfig {
    public static final String boostrapservers = "172.21.0.5:9092";
    public static final String acks = "1";
    public static final String topic = "Topic-Test";
    public static final String keySerializer= LongSerializer.class.getName();
    public static final String valueSerializer = StringSerializer.class.getName();
}
