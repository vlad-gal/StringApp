package by.halatsevich.string.reader;

import by.halatsevich.string.exception.InputDataException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class TextReaderTest {
    TextReader reader;
    static final String TEST_FILE = "resources/data/input.txt";

    @BeforeClass
    public void setUp() {
        reader = new TextReader();
    }

    @Test
    public void testReadStringFromFileSuccess() throws InputDataException {
        String actual = reader.readStringFromFile(TEST_FILE);
        String expected = "В половине прошлого столетия по дворам села Хабаровки бегала в затрапезном платье " +
                "босоногая, но веселая, толстая и краснощекая девка Наташка. По заслугам и просьбе отца ее, " +
                "кларнетиста Саввы, дед мой взял ее в верх - находиться в числе женской прислуги бабушки. " +
                "Горничная Наташка отличалась в этой должности кротостью нрава и усердием. ";
        assertEquals(actual, expected);
    }

    @Test
    public void testReadStringFromFileFailure() throws InputDataException {
        String actual = reader.readStringFromFile(TEST_FILE);
        String expected = "";
        assertNotEquals(actual, expected);
    }

    @Test
    public void testReadStringFromDefaultFile() throws InputDataException {
        String actual = reader.readStringFromFile("1");
        String expected = "В половине прошлого столетия по дворам села Хабаровки бегала в затрапезном платье " +
                "босоногая, но веселая, толстая и краснощекая девка Наташка. По заслугам и просьбе отца ее, " +
                "кларнетиста Саввы, дед мой взял ее в верх - находиться в числе женской прислуги бабушки. " +
                "Горничная Наташка отличалась в этой должности кротостью нрава и усердием. ";
        assertEquals(actual, expected);
    }

    @Test
    public void testReadStringFromConsoleSuccess() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("4 4 5 asd 7 4".getBytes());
        String actual = reader.readStringFromConsole(byteArrayInputStream);
        String expected = "4 4 5 asd 7 4 ";
        assertEquals(actual, expected);
    }

    @Test
    public void testReadStringFromConsoleFailure() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("4 4 5 asd 7 4".getBytes());
        String actual = reader.readStringFromConsole(byteArrayInputStream);
        String expected = "4";
        assertNotEquals(actual, expected);
    }
}