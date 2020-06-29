package by.halatsevich.string.reader;

import by.halatsevich.string.exception.InputDataException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class StringReader {
    private static final InputStream DEFAULT_INPUT = System.in;
    private static final String DEFAULT_PATH = "resources/data/input.txt";

    /**
     * Read all data from file
     *
     * @param filePath path to exist file
     * @return array of strings
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
            resultText.append(line + " ");
        }
        return resultText.toString();
    }

    /**
     * Read all data from console
     *
     * @param input input stream to be scanned
     * @return array of strings
     * @throws InputDataException if size of array is less than 0
     */
    public String readStringFromConsole(InputStream input) throws InputDataException {
        if (input == null) {
            input = DEFAULT_INPUT;
        }
        Scanner scanner = new Scanner(input);
        StringBuilder resultText = new StringBuilder();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.matches("exit")) {
                break;
            }
            resultText.append(line).append(" ");
        }
        scanner.close();
        return resultText.toString();
    }
}
