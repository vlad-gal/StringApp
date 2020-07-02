package by.halatsevich.string.service.impl;

import by.halatsevich.string.exception.InputDataException;
import by.halatsevich.string.service.DeleteSymbol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements DeleteSymbol interface, and implements methods by means regular expressions
 *
 * @author Vladislav Halatsevich
 */
public class RegExDeleteSymbolServiceImpl implements DeleteSymbol {
    private static final String REGEX_ALL_NON_LETTERS = "\\s+-\\s+|[^-\\P{Punct}]|\\s+|\\d";
    private static final String BLANK = "";
    private static final String SPACE = " ";
    private static final String REGEX_CONSONANT_WORD = "\\b[^aeiouyAEIOUYаеёиоуэыюяАЕЁИОУЭЫЮЯ\\s]\\S{%d}\\b";

    /**
     * This method delete all non letters in a text
     *
     * @param text text where need to delete all non letters
     * @return text with deleted non letters
     * @throws InputDataException if text is null
     */
    @Override
    public String deleteAllNonLetters(String text) throws InputDataException {
        if (text == null) {
            throw new InputDataException("Text is null");
        }
        Pattern pattern = Pattern.compile(REGEX_ALL_NON_LETTERS);
        Matcher matcher = pattern.matcher(text);
        StringBuffer changedText = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(changedText, SPACE);
        }
        matcher.appendTail(changedText);
        return changedText.toString();
    }

    /**
     * This method delete words with a specific length if first character in them is consonant
     *
     * @param text       text where need to delete words with first consonant character
     * @param wordLength word length
     * @return text with deleted words with first consonant character
     * @throws InputDataException if text is null or length of word is less than 0
     */
    @Override
    public String deleteWordsWithConsonantAtFirstLetter(String text, int wordLength) throws InputDataException {
        if (text == null || wordLength < 0) {
            throw new InputDataException("Text is null or length of word is less than 0");
        }
        String wordToDelete = String.format(REGEX_CONSONANT_WORD, (wordLength - 1));
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
