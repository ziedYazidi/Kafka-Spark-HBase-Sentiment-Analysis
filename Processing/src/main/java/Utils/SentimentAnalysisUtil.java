package Utils;

import scala.reflect.internal.util.Collections;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SentimentAnalysisUtil {
    public static Set<String> loadFile(String pathToFile){
        Set<String> fileContent = new HashSet<>();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
            while ((line = reader.readLine()) != null){
                fileContent.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            return fileContent;
        }
    }
}

