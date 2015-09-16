package moderate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InterruptedBubbleSort {
    public static void main(String[] args) throws IOException {
        try (
                BufferedReader buffer = new BufferedReader(new FileReader(args[0]))
        ) {
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] input = line.split("\\|");
                long[] values = toLongArray(input[0].trim().split(" "));
                long iterCount = Long.valueOf(input[1].trim());
                values = bubbleSort(values, iterCount);
                System.out.println(toString(values));
            }
        }
    }

    private static long[] toLongArray(String[] valuesString) {
        long[] values = new long[valuesString.length];
        for (int i = 0; i < valuesString.length; i++) {
            values[i] = Long.valueOf(valuesString[i]);
        }
        return values;
    }

    private static long[] bubbleSort(long[] values, long iterCount) {
        long swapTmp;
        boolean modified = true;
        while (iterCount-- > 0 && modified) {
            modified = false;
            for (int pos = 0; pos < values.length - 1; pos++) {
                if (values[pos] > values[pos + 1]) {
                    swapTmp = values[pos + 1];
                    values[pos + 1] = values[pos];
                    values[pos] = swapTmp;
                    modified = true;
                }
            }
        }
        return values;
    }

    private static String toString(long[] values) {
        StringBuilder sb = new StringBuilder();
        for (long value : values) {
            sb.append(value);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}