import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Promotion {
    private static final String VOWELS = "aeiouyAEIOUY";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ";

    public static void main (String[] args) throws IOException {
//        File file = new File(args[0]);
//        BufferedReader buffer = new BufferedReader(new FileReader(file));
//        String line;
//        while ((line = buffer.readLine()) != null) {
        String line = "Jack Abraham,John Evans,Ted Dziuba;iPad 2 - 4-pack,Girl Scouts Thin Mints,Nerf Crossbow";
            String[] input = line.trim().split(";");
            String[] customers = input[0].split(",");
            String[] products = input[1].split(",");
            float ss = calculateMaxSS(customers, products);
            System.out.println(ss);
        int i = line.getBytes().length;
//        }
    }

    private static float calculateMaxSS(String[] customers, String[] products) {
        float[][] customerProducts = new float[customers.length][products.length];
        boolean[][] permut = new boolean[customers.length][products.length];
        System.out.println(permut[1][1]);
        for (int i = 0; i < customers.length; i++) {
            for (int j = 0; j < products.length; j++) {
                customerProducts[i][j] = getSS(customers[i], products[j]);
                permut[i][j] = i == j;
            }
        }
        return 0;
    }

    private void init(boolean[][] permut) {
        for (int i = 0; i < permut.length; i++) {
            for (int j = 0; j < permut[0].length; i++) {
                permut[i][j] = false;
            }
        }
    }

    private static float getSS(String customer, String product) {
        float ss;
        if (getLettersCount(product) % 2 == 0) {
            ss = getVowelsCount(customer) * 1.5f;
        } else {
            ss = getConsonantsCount(customer);
        }
        if (hasCommonFactorBiggerThanOne(getLettersCount(customer), getLettersCount(product))) {
            ss *= 1.5f;
        }
        return ss;
    }

    private static int getVowelsCount(String customer) {
        int count = 0;
        for (char c : customer.toCharArray()) {
            if (isVowel(c)) {
                count++;
            }
        }
        return count;
    }

    private static int getConsonantsCount(String customer) {
        int count = 0;
        for (char c : customer.toCharArray()) {
            if (isConsonant(c)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isVowel(char c) {
        return VOWELS.contains(String.valueOf(c));
    }

    private static boolean isConsonant(char c) {
        return CONSONANTS.contains(String.valueOf(c));
    }

    private static int getLettersCount(String word) {
        return getVowelsCount(word) + getConsonantsCount(word);
    }

    private static boolean hasCommonFactorBiggerThanOne(int num1, int num2) {
        int limit = Math.min(num1, num2);
        for (int i = 2; i <= limit; i++) {
            if (num1 % i == 0 && num2 % i == 0) {
                return true;
            }
        }
        return false;
    }
}
