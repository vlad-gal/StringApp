package by.halatsevich.string.service.impl;

import by.halatsevich.string.service.DeleteSymbol;

public class ArrayDeleteSymbolServiceImpl implements DeleteSymbol {
    private static final String ALL_CONSONANTS = "bcdfghjlkmnpqrstvwxzбвгджзйклмнпрстфхцчшщ" +
            "BCDFGHJLKMNPQRSTVWXZБВГДЖЗЙКЛМНПРСТФХЦЧШЩ";
    private static final char HYPHEN = '-';
    private static final char SPACE = ' ';

    @Override
    public String deleteAllNonLetters(String text) {
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

    @Override
    public String deleteWordsWithConsonantAtFirstLetter(String text, int wordLength) {
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


    private int calculateWordLength(char[] characters, int index) {
        int size = 0;
        int tempIndex = index;
        if (index > 0) {
            char beforeChar = characters[index - 1];
            if (Character.isAlphabetic(beforeChar) || characters[index - 1] == HYPHEN) {
                return size;
            }
        }
        char firstChar = characters[tempIndex];
        if (Character.isAlphabetic(firstChar)) {
            while (tempIndex < characters.length) {
                if (Character.isAlphabetic(characters[tempIndex]) || characters[tempIndex] == HYPHEN) {
                    size++;
                    tempIndex++;
                } else {
                    break;
                }
            }
        }
        return size;
    }
}
