package easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StringMask {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            String[] input = line.split(" ");
            String output = applyMask(input[0], input[1]);
            System.out.println(output);
        }
    }

    private static String applyMask(String word, String mask) {
        StringBuilder sb = new StringBuilder(word.length());
        for (int i = 0; i < mask.length(); i++) {
            String letter = String.valueOf(word.charAt(i));
            if (mask.charAt(i) == '1') {
                sb.append(letter.toUpperCase());
            } else {
                sb.append(letter.toLowerCase());
            }
        }
        return sb.toString();
    }
}
