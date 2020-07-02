package by.halatsevich.string.service.impl;

import by.halatsevich.string.exception.InputDataException;
import by.halatsevich.string.service.DeleteSymbol;

/**
 * This class implements DeleteSymbol interface, and implements by means string methods
 *
 * @author Vladislav Halatsevich
 */
public class StringDeleteSymbolServiceImpl implements DeleteSymbol {
    private static final String SPACE = " ";
    private static final String BLANK = "";
    private static final String REGEX_ALL_NON_LETTERS = "\\s+-\\s+|[^-\\P{Punct}]|\\s+|\\d";
    private static final String ALL_CONSONANTS = "bcdfghjlkmnpqrstvwxzбвгджзйклмнпрстфхцчшщ" +
            "BCDFGHJLKMNPQRSTVWXZБВГДЖЗЙКЛМНПРСТФХЦЧШЩ";
    private static final String REGEX_DELIMITER = "\\s+-\\s+|[^-\\P{Punct}]|\\s+";
    private static final String REGEX_WORD_DELETE = "\\b%s\\b";

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
        return text.replaceAll(REGEX_ALL_NON_LETTERS, SPACE);
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
        String[] words = text.split(REGEX_DELIMITER);
        char[] consonants = ALL_CONSONANTS.toCharArray();
        for (String word : words) {
            if (word.length() != wordLength) {
                continue;
            }
            for (char consonant : consonants) {
                if (word.charAt(0) == consonant) {
                    String wordToDelete = String.format(REGEX_WORD_DELETE, word);
                    text = text.replaceFirst(wordToDelete, BLANK);
                }
            }
        }
        return text;
    }
}
