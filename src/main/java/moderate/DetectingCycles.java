package moderate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DetectingCycles {
    public static void main (String[] args) throws IOException {
        try (
                BufferedReader buffer = new BufferedReader(new FileReader(args[0]))
        ) {
            String line;
            while ((line = buffer.readLine()) != null) {
                String cycle = getCycle(line);
                System.out.println(cycle);
            }
        }
    }

    public static String getCycle(String line) {
        String[] elems = line.split(" ");
        if (elems.length < 2) {
            return line.trim();
        }
        String right = "";
        String left = sumElems(elems, 0, "");
        for (int i = elems.length - 1; i >= elems.length / 2; i--) {
            right = elems[i] + right;
            left = left.substring(0, left.length() - elems[i].length());
            if (left.endsWith(right)) {
                return sumElems(elems, i, " ");
            }
        }
        return "";
    }

    private static String sumElems(String[] elems, int start, String separator) {
        StringBuilder sb = new StringBuilder();
        int i = start;
        for (; i < elems.length - 1; i++) {
            sb.append(elems[i]);
            sb.append(separator);
        }
        sb.append(elems[i]);
        return sb.toString();
    }
}
