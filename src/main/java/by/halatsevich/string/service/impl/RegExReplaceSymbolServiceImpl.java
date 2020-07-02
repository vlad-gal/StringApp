package by.halatsevich.string.service.impl;

import by.halatsevich.string.exception.InputDataException;
import by.halatsevich.string.service.ReplaceSymbol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements ReplaceSymbol interface, and implements methods by means regular expressions
 *
 * @author Vladislav Halatsevich
 */
public class RegExReplaceSymbolServiceImpl implements ReplaceSymbol {
    private static final String REGEX_SUBSTRING_WORD = "\\b\\S{%d}\\b";
    private static final String REGEX_WORD_DELIMITER = "\\b\\S+\\b";

    /**
     * This method replaces one character by index in a word. If index > word length, don't replace
     *
     * @param text   text where characters need to replace
     * @param index  position in a word where need to replace character
     * @param letter replaced character
     * @return text with replaced characters in words
     * @throws InputDataException if text is null or index is less than 0
     */
    @Override
    public String replaceLetterByIndex(String text, int index, char letter) throws InputDataException {
        if (text == null || index < 0) {
            throw new InputDataException("Text is null or index is less than 0");
        }
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

    /**
     * This method replaces wrong character if character before wrong matches the passed character
     *
     * @param text              text where wrong characters need to replace
     * @param letterBeforeWrong character before wrong
     * @param wrongLetter       wrong character which need to replace
     * @param rightLetter       replaced right character
     * @return text with replaced wrong characters in words
     * @throws InputDataException if text is null
     */
    @Override
    public String replaceWrongLetter(String text, char letterBeforeWrong, char wrongLetter, char rightLetter)
            throws InputDataException {
        if (text == null) {
            throw new InputDataException("Text is null");
        }
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

    /**
     * This method replaces a word with a specific length to substring
     *
     * @param text       text where words need to replace
     * @param wordLength word length
     * @param substring  replaced substring
     * @return text with replaced words with a specific length to substring
     * @throws InputDataException if text is null or length of word is less than 0
     */
    @Override
    public String replaceSubstring(String text, int wordLength, String substring) throws InputDataException {
        if (text == null || wordLength < 0) {
            throw new InputDataException("Text is null or length of word is less than 0");
        }
        String patternToFind = String.format(REGEX_SUBSTRING_WORD, wordLength);
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
