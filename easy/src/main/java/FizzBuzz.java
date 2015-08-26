import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FizzBuzz {
    private static class FizzBuzzConfig {
        private int fizzValue;
        private int buzzValue;
        private int max;

        private FizzBuzzConfig(int fizzValue, int buzzValue, int max) {
            this.fizzValue = fizzValue;
            this.buzzValue = buzzValue;
            this.max = max;
        }

        public int getFizzValue() {
            return fizzValue;
        }

        public int getBuzzValue() {
            return buzzValue;
        }

        public int getMax() {
            return max;
        }
    }

    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            FizzBuzzConfig fizzBuzzConfig = getFizzBuzzData(line.trim());
            String fizzBuzzResult = getFizzBuzz(fizzBuzzConfig);
            System.out.println(fizzBuzzResult);
        }
    }

    private static String getFizzBuzz(FizzBuzzConfig fizzBuzzConfig) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= fizzBuzzConfig.getMax(); i++) {
            sb.append(getSingleFizzBuzz(i, fizzBuzzConfig.getFizzValue(), fizzBuzzConfig.getBuzzValue()));
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static String getSingleFizzBuzz(int value, int fizzValue, int buzzValue) {
        String fizzBuzzValue = "";
        if (value % fizzValue == 0) {
            fizzBuzzValue += "F";
        }
        if (value % buzzValue == 0) {
            fizzBuzzValue += "B";
        }
        if (fizzBuzzValue.isEmpty()) {
            fizzBuzzValue += value;
        }
        return fizzBuzzValue;
    }

    private static FizzBuzzConfig getFizzBuzzData(String line) {
        String[] values = line.split(" ");
        return new FizzBuzzConfig(Integer.valueOf(values[0]), Integer.valueOf(values[1]), Integer.valueOf(values[2]));
    }
}