package by.halatsevich.string.service.impl;

import by.halatsevich.string.service.DeleteSymbol;

public class StringDeleteSymbolServiceImpl implements DeleteSymbol {
    private static final String SPACE = " ";
    private static final String BLANK = "";
    private static final String REGEX_ALL_NON_LETTERS = "\\s+-\\s+|[^-\\P{Punct}]|\\s+|\\d";
    private static final String ALL_CONSONANTS = "bcdfghjlkmnpqrstvwxzбвгджзйклмнпрстфхцчшщ" +
            "BCDFGHJLKMNPQRSTVWXZБВГДЖЗЙКЛМНПРСТФХЦЧШЩ";
    private static final String REGEX_DELIMITER = "\\s+-\\s+|[^-\\P{Punct}]|\\s+";
    private static final String REGEX_WORD_DELETE = "\\b";

    @Override
    public String deleteAllNonLetters(String text) {
        return text.replaceAll(REGEX_ALL_NON_LETTERS, SPACE);
    }

    @Override
    public String deleteWordsWithConsonantAtFirstLetter(String text, int wordLength) {
        String[] words = text.split(REGEX_DELIMITER);
        char[] consonants = ALL_CONSONANTS.toCharArray();
        for (String word : words) {
            if (word.length() != wordLength) {
                continue;
            }
            for (int i = 0; i < consonants.length; i++) {
                if (word.charAt(0) == consonants[i]) {
                    String wordToDelete = REGEX_WORD_DELETE + word + REGEX_WORD_DELETE;
                    text = text.replaceFirst(wordToDelete, BLANK);
                }
            }
        }
        return text;
    }
}
