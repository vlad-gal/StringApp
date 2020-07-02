package by.halatsevich.string.service.impl;

import by.halatsevich.string.service.ReplaceSymbol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExReplaceSymbolServiceImpl implements ReplaceSymbol {
    private static final String REGEX_SUBSTRING_BEFORE_WORD = "\\b\\S{";
    private static final String REGEX_SUBSTRING_AFTER_WORD = "}\\b";
    private static final String REGEX_WORD_DELIMITER = "\\b\\S+\\b";

    @Override
    public String replaceLetterByIndex(String text, int index, char letter) {
        Pattern pattern = Pattern.compile(REGEX_WORD_DELIMITER);
        Matcher matcher = pattern.matcher(text);
        StringBuffer changedText = new StringBuffer();
        while (matcher.find()) {
            String word = matcher.group();
            if (word.length() <= index) {
                continue;
            }
            String firstPart = word.substring(0, index);
            String secondPart = word.substring(index + 1, word.length());
            String newWord = firstPart + letter + secondPart;
            matcher.appendReplacement(changedText, newWord);
        }
        matcher.appendTail(changedText);
        return changedText.toString();
    }

    @Override
    public String replaceWrongLetter(String text, char letterBeforeWrong, char wrongLetter, char rightLetter) {
        String patternToFind = Character.toString(letterBeforeWrong) + Character.toString(wrongLetter);
        Pattern pattern = Pattern.compile(patternToFind);
        Matcher matcher = pattern.matcher(text);
        StringBuffer changedText = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(changedText,
                    Character.toString(letterBeforeWrong) + Character.toString(rightLetter));
        }
        matcher.appendTail(changedText);
        return changedText.toString();
    }

    @Override
    public String replaceSubstring(String text, int wordLength, String substring) {
        String patternToFind = REGEX_SUBSTRING_BEFORE_WORD + wordLength + REGEX_SUBSTRING_AFTER_WORD;
        Pattern pattern = Pattern.compile(patternToFind);
        Matcher matcher = pattern.matcher(text);
        StringBuffer changedText = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(changedText, substring);
        }
        matcher.appendTail(changedText);
        return changedText.toString();
    }
}
