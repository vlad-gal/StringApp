package by.halatsevich.string.service;

import by.halatsevich.string.exception.InputDataException;

/**
 * This interface provides methods to replace letters or characters in text
 *
 * @author Vladislav Halatsevich
 */
public interface ReplaceSymbol {

    /**
     * This method replaces one character by index in a word. If index > word length, don't replace
     *
     * @param text   text where characters need to replace
     * @param index  position in a word where need to replace character
     * @param letter replaced character
     * @return text with replaced characters in words
     * @throws InputDataException if text is null or index is less than 0
     */
    String replaceLetterByIndex(String text, int index, char letter) throws InputDataException;

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
    String replaceWrongLetter(String text, char letterBeforeWrong, char wrongLetter, char rightLetter)
            throws InputDataException;

    /**
     * This method replaces a word with a specific length to substring
     *
     * @param text       text where words need to replace
     * @param wordLength word length
     * @param substring  replaced substring
     * @return text with replaced words with a specific length to substring
     * @throws InputDataException if text is null or length of word is less than 0
     */
    String replaceSubstring(String text, int wordLength, String substring) throws InputDataException;
}
