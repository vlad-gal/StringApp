package by.halatsevich.string.service.impl;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class StringReplaceSymbolServiceImplTest {
    StringReplaceSymbolServiceImpl replaceSymbolService;
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
        replaceSymbolService = new StringReplaceSymbolServiceImpl();
    }

    @Test
    public void testReplaceLetterByIndexSuccess() {
        String actual = replaceSymbolService.replaceLetterByIndex(text, 4, 'Ё');
        assertEquals(actual, expectedTextForReplaceLetterByIndex);
    }

    @Test
    public void testReplaceLetterByIndexFailure() {
        String actual = replaceSymbolService.replaceLetterByIndex(text, 4, 'Ё');
        String expected = "В половине столетия кое-где по дворЁм села Хабаровки бегала";
        assertNotEquals(actual, expected);
    }

    @Test
    public void testReplaceWrongLetterSuccess() {
        String actual = replaceSymbolService.replaceWrongLetter(text, 'п', 'о', 'О');
        assertEquals(actual, expectedTextForReplaceWrongLetter);
    }

    @Test
    public void testReplaceWrongLetterFailure() {
        String actual = replaceSymbolService.replaceWrongLetter(text, 'п', 'о', 'О');
        String expected = "";
        assertNotEquals(actual, expected);
    }

    @Test
    public void testReplaceSubstringSuccess() {
        String actual = replaceSymbolService.replaceSubstring(text, 6, "HELLO");
        assertEquals(actual, expectedTextForReplaceSubstring);
    }

    @Test
    public void testReplaceSubstringFailure() {
        String actual = replaceSymbolService.replaceSubstring(text, 6, "HELLO");
        String expected = "";
        assertNotEquals(actual, expected);
    }
}