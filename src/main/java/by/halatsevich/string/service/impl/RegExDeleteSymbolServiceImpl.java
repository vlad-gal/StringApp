package by.halatsevich.string.service.impl;

import by.halatsevich.string.service.DeleteSymbol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExDeleteSymbolServiceImpl implements DeleteSymbol {
    private static final String REGEX_ALL_NON_LETTERS = "\\s+-\\s+|[^-\\P{Punct}]|\\s+|\\d";
    private static final String BLANK = "";
    private static final String SPACE = " ";
    private static final String REGEX_CONSONANT_WORD_BEFORE = "\\b[^aeiouyAEIOUYаеёиоуэыюяАЕЁИОУЭЫЮЯ\\s]\\S{";
    private static final String REGEX_CONSONANT_WORD_AFTER = "}\\b";

    @Override
    public String deleteAllNonLetters(String text) {
        Pattern pattern = Pattern.compile(REGEX_ALL_NON_LETTERS);
        Matcher matcher = pattern.matcher(text);
        StringBuffer changedText = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(changedText, SPACE);
        }
        matcher.appendTail(changedText);
        return changedText.toString();
    }

    @Override
    public String deleteWordsWithConsonantAtFirstLetter(String text, int wordLength) {
        String wordToDelete = REGEX_CONSONANT_WORD_BEFORE + (wordLength - 1) + REGEX_CONSONANT_WORD_AFTER;
        Pattern pattern = Pattern.compile(wordToDelete);
        Matcher matcher = pattern.matcher(text);
        StringBuffer changedText = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(changedText, BLANK);
        }
        matcher.appendTail(changedText);
        return changedText.toString();
    }
}
