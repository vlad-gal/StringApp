package by.halatsevich.string.service.impl;

import by.halatsevich.string.exception.InputDataException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class RegExReplaceSymbolServiceImplTest {
    RegExReplaceSymbolServiceImpl replaceSymbolService;
    String text = "В половине столетия кое-где по дворам села " +
            "Хабаровки бегала в затрапезном платье босоногая, но веселая, толстая " +
            "и краснощекая девка Наташка.";
    String expectedTextForReplaceLetterByIndex = "В полоЁине столЁтия кое-Ёде по дворЁм села " +
            "ХабаЁовки бегаЁа в затрЁпезном платЁе босоЁогая, но весеЁая, толсЁая " +
            "и красЁощекая девкЁ НатаЁка.";
    String expectedTextForReplaceWrongLetter = "В пОловине столетия кое-где пО дворам села " +
            "Хабаровки бегала в затрапезном платье босоногая, но веселая, толстая " +
            "и краснощекая девка Наташка.";
    String expectedTextForReplaceSubstring = "В половине столетия кое-где по HELLO села " +
            "Хабаровки HELLO в затрапезном HELLO босоногая, но веселая, толстая " +
            "и краснощекая девка Наташка.";

    @BeforeClass
    public void setUp() {
        replaceSymbolService = new RegExReplaceSymbolServiceImpl();
    }

    @Test
    public void testReplaceLetterByIndexSuccess() throws InputDataException {
        String actual = replaceSymbolService.replaceLetterByIndex(text, 4, 'Ё');
        assertEquals(actual, expectedTextForReplaceLetterByIndex);
    }

    @Test
    public void testReplaceLetterByIndexFailure() throws InputDataException {
        String actual = replaceSymbolService.replaceLetterByIndex(text, 4, 'Ё');
        String expected = "В половине столетия кое-где по дворЁм села Хабаровки бегала";
        assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = InputDataException.class,
            expectedExceptionsMessageRegExp = "Text is null or index is less than 0")
    public void testReplaceLetterByIndexNullTextExceptionMessage() throws InputDataException {
        replaceSymbolService.replaceLetterByIndex(null, 4, 'Ё');
    }

    @Test(expectedExceptions = InputDataException.class,
            expectedExceptionsMessageRegExp = "Text is null or index is less than 0")
    public void testReplaceLetterByIndexInvalidIndexExceptionMessage() throws InputDataException {
        replaceSymbolService.replaceLetterByIndex(text, -4, 'Ё');
    }

    @Test
    public void testReplaceWrongLetterSuccess() throws InputDataException {
        String actual = replaceSymbolService.replaceWrongLetter(text, 'п', 'о', 'О');
        assertEquals(actual, expectedTextForReplaceWrongLetter);
    }

    @Test
    public void testReplaceWrongLetterFailure() throws InputDataException {
        String actual = replaceSymbolService.replaceWrongLetter(text, 'п', 'о', 'О');
        String expected = "";
        assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = InputDataException.class, expectedExceptionsMessageRegExp = "Text is null")
    public void testReplaceWrongLetterNullTextExceptionMessage() throws InputDataException {
        replaceSymbolService.replaceWrongLetter(null, 'п', 'о', 'О');
    }

    @Test
    public void testReplaceSubstringSuccess() throws InputDataException {
        String actual = replaceSymbolService.replaceSubstring(text, 6, "HELLO");
        assertEquals(actual, expectedTextForReplaceSubstring);
    }

    @Test
    public void testReplaceSubstringFailure() throws InputDataException {
        String actual = replaceSymbolService.replaceSubstring(text, 6, "HELLO");
        String expected = "";
        assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = InputDataException.class,
            expectedExceptionsMessageRegExp = "Text is null or length of word is less than 0")
    public void testReplaceSubstringNullTextExceptionMessage() throws InputDataException {
        replaceSymbolService.replaceSubstring(null, 6, "HELLO");
    }

    @Test(expectedExceptions = InputDataException.class,
            expectedExceptionsMessageRegExp = "Text is null or length of word is less than 0")
    public void testReplaceSubstringWordLengthExceptionMessage() throws InputDataException {
        replaceSymbolService.replaceSubstring(text, -6, "HELLO");
    }
}