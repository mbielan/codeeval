import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        try (
                BufferedReader buffer = new BufferedReader(new FileReader(args[0]))
        ) {
            String line;
            while ((line = buffer.readLine()) != null) {
                //TODO
            }
        }
    }
}
