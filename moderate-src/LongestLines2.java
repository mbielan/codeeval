import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class LongestLines2 {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line = buffer.readLine();
        int count = Integer.valueOf(line);
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        };
        TreeSet<String> sentences = new TreeSet<>(comparator);

        while ((line = buffer.readLine()) != null) {
            sentences.add(line);
        }

        Iterator<String> iterator = sentences.iterator();
        for (int i = 0; i < count; i++) {
            System.out.println(iterator.next());
        }
    }
}
