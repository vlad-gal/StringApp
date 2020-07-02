package by.halatsevich.string.service.impl;

import by.halatsevich.string.exception.InputDataException;
import by.halatsevich.string.service.DeleteSymbol;

/**
 * This class implements DeleteSymbol interface, and implements methods by means array of characters
 *
 * @author Vladislav Halatsevich
 */
public class ArrayDeleteSymbolServiceImpl implements DeleteSymbol {
    private static final String ALL_CONSONANTS = "bcdfghjlkmnpqrstvwxzбвгджзйклмнпрстфхцчшщ" +
            "BCDFGHJLKMNPQRSTVWXZБВГДЖЗЙКЛМНПРСТФХЦЧШЩ";
    private static final char HYPHEN = '-';
    private static final char SPACE = ' ';

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
        char[] characters = text.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == HYPHEN && Character.isAlphabetic(characters[i + 1])) {
                continue;
            }
            if (Character.isAlphabetic(characters[i]) || Character.isSpaceChar(characters[i])) {
                continue;
            }
            characters[i] = SPACE;
        }
        return new String(characters);
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
        char[] characters = text.toCharArray();
        char[] consonants = ALL_CONSONANTS.toCharArray();
        int length;
        for (int i = 0; i < characters.length; i++) {
            if (!Character.isAlphabetic(characters[i])) {
                continue;
            }
            int j = 0;
            while (j < consonants.length) {
                if (characters[i] == consonants[j]) {
                    length = calculateWordLength(characters, i);
                    if (length == wordLength) {
                        for (int k = 0; k <= length; k++) {
                            if (i + k == characters.length) {
                                characters[characters.length - 1] = SPACE;
                            } else {
                                characters[i + k] = SPACE;
                            }
                        }
                    }
                    break;
                }
                j++;
            }
        }
        return new String(characters);
    }

    /**
     * Additional method for determining the length of a word
     *
     * @param characters array of characters where need to find word length
     * @param index      position from which start count length
     * @return length of word
     */
    private int calculateWordLength(char[] characters, int index) {
        int length = 0;
        int tempIndex = index;
        char firstChar = characters[tempIndex];
        if (index > 0) {
            char beforeChar = characters[index - 1];
            if (Character.isAlphabetic(beforeChar) || characters[index - 1] == HYPHEN) {
                return length;
            }
        }
        if (Character.isAlphabetic(firstChar)) {
            while (tempIndex < characters.length) {
                if (Character.isAlphabetic(characters[tempIndex]) || characters[tempIndex] == HYPHEN) {
                    length++;
                    tempIndex++;
                } else {
                    break;
                }
            }
        }
        return length;
    }
}
