package moderate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RemoveCharacters {
    public static void main (String[] args) throws IOException {
        try (
                BufferedReader buffer = new BufferedReader(new FileReader(args[0]))
        ) {
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] input = line.split(",");
                System.out.println(removeCharacters(input[0].trim(), input[1].trim()));
            }
        }
    }

    private static String removeCharacters(String text, String charsToRemove) {
        return text.replaceAll("[" + charsToRemove + "]", "");
    }
}
