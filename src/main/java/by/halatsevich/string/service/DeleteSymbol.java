package by.halatsevich.string.service;

import by.halatsevich.string.exception.InputDataException;

/**
 * This interface provides methods to delete letters or characters in text
 *
 * @author Vladislav Halatsevich
 */
public interface DeleteSymbol {

    /**
     * This method delete all non letters in a text
     *
     * @param text text where need to delete all non letters
     * @return text with deleted non letters
     * @throws InputDataException if text is null
     */
    String deleteAllNonLetters(String text) throws InputDataException;

    /**
     * This method delete words with a specific length if first character in them is consonant
     *
     * @param text       text where need to delete words with first consonant character
     * @param wordLength word length
     * @return text with deleted words with first consonant character
     * @throws InputDataException if text is null or length of word is less than 0
     */
    String deleteWordsWithConsonantAtFirstLetter(String text, int wordLength) throws InputDataException;
}
