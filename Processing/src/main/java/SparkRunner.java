import consumer.KafkaConsumer;
import jobs.SentimentAnalysis;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.spark.HBaseContext;
import org.apache.hadoop.hbase.spark.JavaHBaseContext;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.List;

public class SparkRunner {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[4]").setAppName("NetworkWordCount");
        SparkSession sparkSession = SparkSession
                .builder()
                .appName("Spark sentiment analysis")
                .config(conf)
                .getOrCreate();
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
        JavaStreamingContext jssc = new JavaStreamingContext(jsc, Durations.seconds(1));
        KafkaConsumer kafkaConsumer = new KafkaConsumer();
        Configuration hbaseConfiguration = HBaseConfiguration.create();
        JavaHBaseContext javaHBaseContext = new JavaHBaseContext(jsc, hbaseConfiguration);
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
