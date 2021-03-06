package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LongestLines1 {

    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line = buffer.readLine();
        int count = Integer.valueOf(line);
        List<String> sentences = new ArrayList<>();
        while ((line = buffer.readLine()) != null) {
            sentences.add(line);
        }
        Collections.sort(sentences, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length() ;
            }
        });
        for (int i = 0; i < count; i++) {
            System.out.println(sentences.get(i));
        }
    }
}
