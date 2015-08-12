import java.io.*;

public class MthToLastElement2 {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            char mthChar = getMthChar(line.trim());
            if (mthChar > 0) {
                System.out.println(mthChar);
            }
        }
    }

    private static char getMthChar(String elems) {
        int mthToLastTextStart = elems.lastIndexOf(' ') + 1;
        String mthToLastText = elems.substring(mthToLastTextStart);
        int mthToLast = Integer.valueOf(mthToLastText);
        int mth = mthToLastTextStart - 2 * mthToLast;
        if (mth < 0) {
            return 0;
        }
        return elems.charAt(mth);
    }
}
