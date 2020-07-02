package by.halatsevich.string.service;

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
     */
    String deleteAllNonLetters(String text);

    /**
     * This method delete words with a specific length if first character in them is consonant
     *
     * @param text       text where need to delete words with first consonant character
     * @param wordLength word length
     * @return text with deleted words with first consonant character
     */
    String deleteWordsWithConsonantAtFirstLetter(String text, int wordLength);
}
