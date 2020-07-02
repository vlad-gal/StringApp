package by.halatsevich.string.service.impl;

import by.halatsevich.string.exception.InputDataException;
import by.halatsevich.string.service.ReplaceSymbol;

/**
 * This class implements ReplaceSymbol interface, and implements by means string methods
 *
 * @author Vladislav Halatsevich
 */
public class StringReplaceSymbolServiceImpl implements ReplaceSymbol {
    private static final String REGEX_DELIMITER = "\\s+-\\s+|[^-\\P{Punct}]|\\s+";
    private static final String REGEX_SUBSTRING_WORD = "\\b\\S{%d}\\b";

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
        String[] words = text.split(REGEX_DELIMITER);
        for (String word : words) {
            if (word.length() <= index) {
                continue;
            }
            StringBuilder changedWord = new StringBuilder(word);
            changedWord.setCharAt(index, letter);
            text = text.replaceFirst(word, changedWord.toString());
        }
        return text;
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
        String[] words = text.split(REGEX_DELIMITER);
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            StringBuilder changedWord = new StringBuilder(word);
            for (int i = 0; i < word.length(); i++) {
                if (word.length() == word.indexOf(wrongLetter)) {
                    continue;
                }
                if (changedWord.charAt(i) == letterBeforeWrong && changedWord.charAt(i + 1) == wrongLetter) {
                    changedWord.setCharAt(i + 1, rightLetter);
                }
            }
            text = text.replaceAll(word, changedWord.toString());
        }
        return text;
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
        String wordToReplace = String.format(REGEX_SUBSTRING_WORD, wordLength);
        text = text.replaceAll(wordToReplace, substring);
        return text;
    }
}
