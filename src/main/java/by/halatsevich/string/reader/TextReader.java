package by.halatsevich.string.reader;

import by.halatsevich.string.exception.InputDataException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Text reader from file and console
 *
 * @author Vladislav Halatsevich
 */
public class TextReader {
    private static final InputStream DEFAULT_INPUT = System.in;
    private static final String DEFAULT_PATH = "resources/data/input.txt";
    private static final String REGEX_EXIT = "exit";
    private static final String SPACE = " ";

    /**
     * Read all text from file
     *
     * @param filePath path to exist file
     * @return string of data
     * @throws InputDataException if error occurred while opening file
     */
    public String readStringFromFile(String filePath) throws InputDataException {
        Path path = Paths.get(filePath);
        if (Files.isDirectory(path) || !Files.exists(path)) {
            path = Paths.get(DEFAULT_PATH);
        }
        List<String> data;
        try {
            data = Files.readAllLines(path);
        } catch (IOException e) {
            throw new InputDataException("Error while opening file", e);
        }
        StringBuilder resultText = new StringBuilder();
        for (String line : data) {
            resultText.append(line).append(SPACE);
        }
        return resultText.toString();
    }

    /**
     * Read all text from console
     *
     * @param input input stream to be scanned
     * @return string of data
     */
    public String readStringFromConsole(InputStream input) {
        if (input == null) {
            input = DEFAULT_INPUT;
        }
        Scanner scanner = new Scanner(input);
        StringBuilder resultText = new StringBuilder();
        System.out.println("Write \"exit\" to stop reading");
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.matches(REGEX_EXIT)) {
                break;
            }
            resultText.append(line).append(SPACE);
        }
        scanner.close();
        return resultText.toString();
    }
}
