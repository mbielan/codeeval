import java.io.*;

public class MthToLastElement1 {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            String mthElem = getMthElem(line.trim().split(" "));
            if (mthElem != null) {
                System.out.println(mthElem);
            }
        }
    }

    private static String getMthElem(String[] elems) {
        int mth = elems.length - Integer.valueOf(elems[elems.length - 1]) - 1;
        if (mth < 0) {
            return null;
        }
        return elems[mth];
    }
}

