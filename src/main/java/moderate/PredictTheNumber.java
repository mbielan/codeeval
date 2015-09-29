package moderate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PredictTheNumber {
    public static void main (String[] args) throws IOException {
        try (
                BufferedReader buffer = new BufferedReader(new FileReader(args[0]))
        ) {
            String line;
            while ((line = buffer.readLine()) != null) {
                System.out.println(predictNumber(Long.valueOf(line)));
            }
        }
    }

    private static int predictNumber(long index) {
        long pow = getPow(index);
        int transformCount = 0;
        while (index > 0) {
            if (index >= pow) {
                index -= pow;
                transformCount++;
            }
            pow /= 2;
        }
        return transformCount % 3;
    }

    private static long getPow(long index) {
        long pow = 1;
        while (pow <= index) {
            pow *= 2;
        }
        return pow / 2;
    }
}
