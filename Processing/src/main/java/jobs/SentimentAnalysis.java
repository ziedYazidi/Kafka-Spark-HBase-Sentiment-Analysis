package jobs;

import Utils.SentimentAnalysisUtil;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.HashSet;
import java.util.Set;

public class SentimentAnalysis {

    //    Load Files
    private Broadcast<Set<String>> uselessWords;
    private Broadcast<Set<String>> positiveWords;
    private Broadcast<Set<String>> negativeWords;

    public SentimentAnalysis(JavaStreamingContext jssc) {
        uselessWords = jssc.sparkContext().broadcast(SentimentAnalysisUtil.loadFile("/home/finaxys/KafkaSparkHBase/Processing/src/main/resources/stop-words.dat"));
        positiveWords = jssc.sparkContext().broadcast(SentimentAnalysisUtil.loadFile("home/finaxys/KafkaSparkHBase/Processing/src/main/resources/pos-words.dat"));
        negativeWords = jssc.sparkContext().broadcast(SentimentAnalysisUtil.loadFile("home/finaxys/KafkaSparkHBase/Processing/src/main/resources/neg-words.dat"));
    }

    public void applySentimentAnalysis(JavaPairDStream stream){
        stream.mapValues( -> )

    }
}
