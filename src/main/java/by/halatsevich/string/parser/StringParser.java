package by.halatsevich.string.parser;

import java.util.ArrayList;
import java.util.List;

public class StringParser {
   // private static final String REGEXP_DELIMITER = "\\s+";
 //     private static final String REGEXP_DELIMITER = "[^\\w+\\-\\w+\\P{Punct}+]|\\s+";
      private static final String REGEXP_DELIMITER = "\\s+-\\s+|[^-\\P{Punct}]|\\s+";

    /**
     * Parse all data to array of integers
     *
     * @param dataToParse array of strings
     * @return parsed array of integers
     */
    public List<String> parseAllText(String textToParse) {
        List<String> result = new ArrayList<>();
        String[] words = textToParse.split(REGEXP_DELIMITER);
        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) { //&& !words[i].equals("-")){
                result.add(words[i]);
            }
        }
        return result;
    }
}
