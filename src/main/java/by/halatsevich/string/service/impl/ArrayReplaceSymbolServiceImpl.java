package by.halatsevich.string.service.impl;

import by.halatsevich.string.service.ReplaceSymbol;

import java.util.ArrayList;
import java.util.List;

public class ArrayReplaceSymbolServiceImpl implements ReplaceSymbol {

    @Override
    public String replaceLetterByIndex(String text, int index, char letter) {
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

    @Override
    public String replaceWrongLetter(String text, char letterBeforeWrong, char wrongLetter, char rightLetter) {
        char[] characters = text.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (!Character.isAlphabetic(characters[i])) {
                continue;
            }
            if ((i + 1) < characters.length) {
                if (characters[i] == letterBeforeWrong && characters[i + 1] == wrongLetter) {
                    characters[i + 1] = rightLetter;
                }
            }
        }
        return new String(characters);
    }

    @Override
    public String replaceSubstring(String text, int wordLength, String substring) {
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
                for (int j = 0; j < substringArray.length; j++) {
                    characterList.add(substringArray[j]);
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

    private int calculateLastIndexInWord(char[] characters, int index) {
        int size = 0;
        int tempIndex = index;
        char firstChar = characters[tempIndex];
        if (index > 0) {
            char beforeChar = characters[index - 1];
            if (Character.isLetterOrDigit(beforeChar) || characters[index - 1] == '-') {
                return size;
            }
        }
        if (Character.isLetterOrDigit(firstChar)) {
            while (tempIndex < characters.length) {
                if (Character.isLetterOrDigit(characters[tempIndex]) || characters[tempIndex] == '-') {
                    size++;
                    tempIndex++;
                } else {
                    break;
                }
            }
        }
        return (size + index - 1);
    }
}
