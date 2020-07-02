package by.halatsevich.string.service.impl;

import by.halatsevich.string.service.ReplaceSymbol;

public class StringReplaceSymbolServiceImpl implements ReplaceSymbol {
    private static final String REGEX_DELIMITER = "\\s+-\\s+|[^-\\P{Punct}]|\\s+";
    private static final String REGEX_SUBSTRING_BEFORE_WORD = "\\b\\S{";
    private static final String REGEX_SUBSTRING_AFTER_WORD = "}\\b";

    @Override
    public String replaceLetterByIndex(String text, int index, char letter) {
        String[] words = text.split(REGEX_DELIMITER);
        for (String word : words) {
            if (word.length() <= index) {
                continue;
            }
            StringBuilder changedWord = new StringBuilder(word);
            changedWord.setCharAt(index, letter);
            text = text.replaceFirst(word, changedWord.toString());
        }
        return text;
    }

    @Override
    public String replaceWrongLetter(String text, char letterBeforeWrong, char wrongLetter, char correctLetter) {
        String[] words = text.split(REGEX_DELIMITER);
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            StringBuilder changedWord = new StringBuilder(word);
            for (int i = 0; i < word.length(); i++) {
                if (word.length() == word.indexOf(wrongLetter)) {
                    continue;
                }
                if (changedWord.charAt(i) == letterBeforeWrong && changedWord.charAt(i + 1) == wrongLetter) {
                    changedWord.setCharAt(i + 1, correctLetter);
                }
            }
            text = text.replaceAll(word, changedWord.toString());
        }
        return text;
    }

    @Override
    public String replaceSubstring(String text, int wordLength, String substring) {
        String wordToReplace = REGEX_SUBSTRING_BEFORE_WORD + wordLength + REGEX_SUBSTRING_AFTER_WORD;
        text = text.replaceAll(wordToReplace, substring);
        return text;
    }
}
