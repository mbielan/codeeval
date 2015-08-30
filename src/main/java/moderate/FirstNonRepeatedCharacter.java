package moderate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirstNonRepeatedCharacter {
    public static void main (String[] args) throws IOException {
        try (
                BufferedReader buffer = new BufferedReader(new FileReader(args[0]))
        ) {
            String line;
            while ((line = buffer.readLine()) != null) {
                System.out.println(getFirstNonRepeatingChar(line));
            }
        }
    }

    private static char getFirstNonRepeatingChar(String line) {
        List<Character> nonRepeatingChars = new ArrayList<>(line.length());
        List<Character> repeatingChars = new ArrayList<>(line.length());
        for (char c : line.toCharArray()) {
            if (!repeatingChars.contains(c)) {
                if (nonRepeatingChars.contains(c)) {
                    repeatingChars.add(c);
                    nonRepeatingChars.remove((Character) c);
                } else {
                    nonRepeatingChars.add(c);
                }
            }
        }
        return nonRepeatingChars.get(0);
    }
}
