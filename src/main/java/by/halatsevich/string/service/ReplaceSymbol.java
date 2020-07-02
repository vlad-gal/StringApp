package by.halatsevich.string.service;

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
     */
    String replaceLetterByIndex(String text, int index, char letter);

    /**
     * This method replaces wrong character if character before wrong matches the passed character
     *
     * @param text              text where wrong characters need to replace
     * @param letterBeforeWrong character before wrong
     * @param wrongLetter       wrong character which need to replace
     * @param rightLetter       replaced right character
     * @return text with replaced wrong characters in words
     */
    String replaceWrongLetter(String text, char letterBeforeWrong, char wrongLetter, char rightLetter);

    /**
     * This method replaces a word with a specific length to substring
     *
     * @param text       text where words need to replace
     * @param wordLength word length
     * @param substring  replaced substring
     * @return text with replaced words with a specific length to substring
     */
    String replaceSubstring(String text, int wordLength, String substring);
}
