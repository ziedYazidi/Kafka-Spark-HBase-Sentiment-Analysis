package sink;

import models.Record;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.api.java.JavaDStream;

import java.util.HashMap;

public class HbaseSink {

    public void saveToHbase(SparkSession sparkSession, JavaDStream<String> stream) {
        StringBuilder catalog = new StringBuilder()
                .append("{")
                .append("\"table\":{\"namespace\":\"default\", \"name\":\"employee\"},\n")
                .append("\"rowkey\":\"key\",")
                .append("\"columns\":{")
                .append("\"key\":{\"cf\":\"rowkey\", \"col\":\"key\", \"type\":\"string\"},")
                .append("\"value\":{\"cf\":\"rowvalue\", \"col\":\"value\", \"type\":\"string\"},\n");
    }
}
