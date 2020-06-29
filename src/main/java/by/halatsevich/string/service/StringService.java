package by.halatsevich.string.service;

import java.util.ArrayList;
import java.util.List;

public class StringService {

    public String replaceIndexLetter(String text, int index, char replacedChar) {
        String[] words = text.split("\\s+-\\s+|[^-\\P{Punct}]|\\s+");
        for (String word : words) {
            if (word.length() <= index) {
                continue;
            }
            StringBuilder changeWord = new StringBuilder(word);
            changeWord.setCharAt(index, replacedChar);
            text = text.replaceFirst(word, changeWord.toString());
        }
        return text;
    }

    public String replaceWrongLetter(String text, char beforeChar, char wrongCharInWord, char correctChar) {
        String[] words = text.split("\\s+-\\s+|[^-\\P{Punct}]|\\s+");
        for (String word : words) {
            if (word.isEmpty() || word.equals("-")) {
                continue;
            }
            StringBuilder changeWord = new StringBuilder(word);
            for (int i = 0; i < word.length(); i++) {
                if (word.indexOf(beforeChar) == word.length()) {
                    continue;
                }
                if (changeWord.charAt(i) == wrongCharInWord && changeWord.charAt(i - 1) == beforeChar) {
                    changeWord.setCharAt(i, correctChar);
                }
            }
            text = text.replaceAll(word, changeWord.toString());
        }
        return text;
    }

    public String replaceSubstring(String text, int length, String substring) { // не работает
        String[] words = text.split("\\s+-\\s+|[^-\\P{Punct}]|\\s+");
        String regexp = String.format("\\b\\S{%s}\\b",length);
        for (String word : words) {
            if (word.matches(regexp)){
                System.out.println(word);
            }
            if (word.length() == length) {
            }
        }
        text = text.replaceAll(regexp,substring);
        return text;
    }

    public String replaceNonLetters(String text, String replacement) {
        return text.replaceAll("\\s+-\\s+|[^-\\P{Punct}]|\\s+|\\d", replacement);
    }

    public String removeStringVowelStart(String text, int length) { //не работает
        String[] words = text.split("\\s+-\\s+|[^-\\P{Punct}]|\\s+");
        for (String word : words) {
            if (word.length() != length) {
                continue;
            }
            text = text.replace(word, "");
        }

        return text;
    }

    public String returnStringLine(List<String> words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word + " ");
        }
        return builder.toString();
    }
}
