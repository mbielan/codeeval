import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReverseWords {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            String reversedSentence = reverseSentence(line.trim());
            if (!reversedSentence.isEmpty()) {
                System.out.println(reversedSentence);
            }
        }
    }

    private static String reverseSentence(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder(sentence.length());
        for (int i = 0; i < words.length; i++) {
            String word = words[words.length - i - 1];
            if (!word.isEmpty()) {
                sb.append(word);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}
