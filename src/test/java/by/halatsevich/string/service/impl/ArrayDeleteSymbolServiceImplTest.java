package by.halatsevich.string.service.impl;

import by.halatsevich.string.exception.InputDataException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ArrayDeleteSymbolServiceImplTest {
    ArrayDeleteSymbolServiceImpl deleteSymbolService;
    String text = "В половине столетия кое-где по дворам села " +
            "Хабаровки бегала в затрапезном платье босоногая, но веселая, толстая " +
            "и краснощекая девка Наташка.";
    String expectedTextForDeleteAllNonLetters = "В половине столетия кое-где по дворам села " +
            "Хабаровки бегала в затрапезном платье босоногая  но веселая  толстая " +
            "и краснощекая девка Наташка ";
    String expectedTextForDeleteConsonants = "В половине столетия кое-где    дворам села " +
            "Хабаровки бегала в затрапезном платье босоногая,    веселая, толстая " +
            "и краснощекая девка Наташка.";

    @BeforeClass
    public void setUp() {
        deleteSymbolService = new ArrayDeleteSymbolServiceImpl();
    }

    @Test
    public void testDeleteAllNonLettersSuccess() throws InputDataException {
        String actual = deleteSymbolService.deleteAllNonLetters(text);
        assertEquals(actual, expectedTextForDeleteAllNonLetters);
    }

    @Test
    public void testDeleteAllNonLettersFailure() throws InputDataException {
        String actual = deleteSymbolService.deleteAllNonLetters(text);
        String expected = "В половине столетия кое где по дворам села " +
                "Хабаровки бегала в затрапезном платье босоногая  но веселая  толстая " +
                "и краснощекая девка Наташка ";
        assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = InputDataException.class,
            expectedExceptionsMessageRegExp = "Text is null")
    public void testDeleteAllNonLettersNullTextExceptionMessage() throws InputDataException {
        deleteSymbolService.deleteAllNonLetters(null);
    }

    @Test
    public void testDeleteWordsWithConsonantAtFirstLetterSuccess() throws InputDataException {
        String actual = deleteSymbolService.deleteWordsWithConsonantAtFirstLetter(text, 2);
        assertEquals(actual, expectedTextForDeleteConsonants);
    }

    @Test
    public void testDeleteWordsWithConsonantAtFirstLetterFailure() throws InputDataException {
        String actual = deleteSymbolService.deleteWordsWithConsonantAtFirstLetter(text, 2);
        String expected = "";
        assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = InputDataException.class,
            expectedExceptionsMessageRegExp = "Text is null or length of word is less than 0")
    public void testDeleteWordsWithConsonantAtFirstLetterNullTextExceptionMessage() throws InputDataException {
        deleteSymbolService.deleteWordsWithConsonantAtFirstLetter(null, 2);
    }

    @Test(expectedExceptions = InputDataException.class,
            expectedExceptionsMessageRegExp = "Text is null or length of word is less than 0")
    public void testDeleteWordsWithConsonantAtFirstLetterWordLengthExceptionMessage() throws InputDataException {
        deleteSymbolService.deleteWordsWithConsonantAtFirstLetter("112", -2);
    }
}