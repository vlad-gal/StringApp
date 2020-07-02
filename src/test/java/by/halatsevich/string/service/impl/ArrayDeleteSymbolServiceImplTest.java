package by.halatsevich.string.service.impl;

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
    public void testDeleteAllNonLettersSuccess() {
        String actual = deleteSymbolService.deleteAllNonLetters(text);
        assertEquals(actual, expectedTextForDeleteAllNonLetters);
    }

    @Test
    public void testDeleteAllNonLettersFailure() {
        String actual = deleteSymbolService.deleteAllNonLetters(text);
        String expected = "В половине столетия кое где по дворам села " +
                "Хабаровки бегала в затрапезном платье босоногая  но веселая  толстая " +
                "и краснощекая девка Наташка ";
        assertNotEquals(actual, expected);
    }

    @Test
    public void testDeleteWordsWithConsonantAtFirstLetterSuccess() {
        String actual = deleteSymbolService.deleteWordsWithConsonantAtFirstLetter(text, 2);
        assertEquals(actual, expectedTextForDeleteConsonants);
    }

    @Test
    public void testDeleteWordsWithConsonantAtFirstLetterFailure() {
        String actual = deleteSymbolService.deleteWordsWithConsonantAtFirstLetter(text, 2);
        String expected = "";
        assertNotEquals(actual, expected);
    }
}