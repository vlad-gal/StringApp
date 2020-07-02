package by.halatsevich.string.service.impl;

import by.halatsevich.string.exception.InputDataException;
import by.halatsevich.string.service.ReplaceSymbol;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements ReplaceSymbol interface, and implements methods by means array of characters
 *
 * @author Vladislav Halatsevich
 */
public class ArrayReplaceSymbolServiceImpl implements ReplaceSymbol {
    private static final char HYPHEN = '-';

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
        char[] characters = text.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (!Character.isAlphabetic(characters[i])) {
                continue;
            }
            int lastIndex = calculateLastIndexInWord(characters, i);
            int j = 0;
            while (j < lastIndex) {
                if (i + index <= lastIndex) {
                    characters[i + index] = letter;
                    break;
                }
                j++;
            }
        }
        return new String(characters);
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
        char[] characters = text.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (!Character.isAlphabetic(characters[i])) {
                continue;
            }
            if ((i + 1) < characters.length &&
                    (characters[i] == letterBeforeWrong && characters[i + 1] == wrongLetter)) {
                characters[i + 1] = rightLetter;
            }
        }
        return new String(characters);
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
        char[] characters = text.toCharArray();
        List<Character> characterList = new ArrayList<>();
        char[] substringArray = substring.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (!Character.isAlphabetic(characters[i])) {
                characterList.add(characters[i]);
                continue;
            }
            int length = calculateLastIndexInWord(characters, i);
            if (length - i + 1 == wordLength) {
                for (char aSubstringArray : substringArray) {
                    characterList.add(aSubstringArray);
                }
                i = length;
            } else {
                characterList.add(characters[i]);
            }
        }
        char[] newCharacters = new char[characterList.size()];
        for (int i = 0; i < newCharacters.length; i++) {
            newCharacters[i] = characterList.get(i);
        }
        return new String(newCharacters);
    }

    /**
     * Additional method for determining the last index in a word
     *
     * @param characters array of characters where need to find last index in a word
     * @param index      position from which start find last index
     * @return last index in a word
     */
    private int calculateLastIndexInWord(char[] characters, int index) {
        int length = 0;
        int tempIndex = index;
        char firstChar = characters[tempIndex];
        if (index > 0) {
            char beforeChar = characters[index - 1];
            if (Character.isLetterOrDigit(beforeChar) || characters[index - 1] == HYPHEN) {
                return length;
            }
        }
        if (Character.isLetterOrDigit(firstChar)) {
            while (tempIndex < characters.length) {
                if (Character.isLetterOrDigit(characters[tempIndex]) || characters[tempIndex] == HYPHEN) {
                    length++;
                    tempIndex++;
                } else {
                    break;
                }
            }
        }
        return (length + index - 1);
    }
}
