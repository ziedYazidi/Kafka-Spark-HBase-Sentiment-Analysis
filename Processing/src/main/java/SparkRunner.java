import consumer.KafkaConsumer;
import jobs.SentimentAnalysis;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.List;

public class SparkRunner {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[4]").setAppName("NetworkWordCount");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(1));
        KafkaConsumer kafkaConsumer = new KafkaConsumer();
        JavaPairDStream<String, List<String>> stream = kafkaConsumer.consumeStream(jssc);
        SentimentAnalysis sentimentAnalysis = new SentimentAnalysis(jssc);
        sentimentAnalysis.applySentimentAnalysis(stream).print();
        jssc.start();
        try {
            jssc.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
