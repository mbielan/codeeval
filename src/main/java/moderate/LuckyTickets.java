package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class LuckyTickets {

    private static final int MAX_RANGE = 50;

    public static void main (String[] args) throws IOException {
        Map<String, BigInteger> luckyTickets = getLuckyTickets();

        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            System.out.println(luckyTickets.get(line.trim()));
        }
    }

    private static Map<String, BigInteger> getLuckyTickets() {
        Map<String, BigInteger> luckyTickets = initLuckyTickets();
        BigInteger[] subSums = initSubSums();

        for (int range = 2; range <= MAX_RANGE; range++) {
            BigInteger luckyTicketsCount = getLuckyTicketsCount(range, subSums);
            luckyTickets.put(String.valueOf(2 * range), luckyTicketsCount);
        }
        return luckyTickets;
    }

    private static BigInteger getLuckyTicketsCount(int range, BigInteger[] subSums) {
        BigInteger luckyTicketsCount = BigInteger.ONE;
        BigInteger[] lastTenValues = new BigInteger[10];
        lastTenValues[0] = BigInteger.ONE;
        BigInteger tmpPrev;
        for (int i = 1; i <= range * 9; i++) {
            tmpPrev = subSums[i];
            subSums[i] = subSums[i].add(subSums[i - 1]);
            if (i > 9) {
                subSums[i] = subSums[i].subtract(lastTenValues[i % 10]);
            }
            lastTenValues[i % 10] = tmpPrev;
            luckyTicketsCount = luckyTicketsCount.add(subSums[i].pow(2));
        }
        return luckyTicketsCount;
    }

    private static Map<String, BigInteger> initLuckyTickets() {
        Map<String, BigInteger> luckyTickets = new HashMap<>();
        luckyTickets.put("2", BigInteger.valueOf(9L));
        return luckyTickets;
    }

    private static BigInteger[] initSubSums() {
        BigInteger[] subSums = new BigInteger[MAX_RANGE * 9 + 1];
        for (int i = 0; i < 10; i++) {
            subSums[i] = BigInteger.ONE;
        }
        for (int i = 10; i < subSums.length; i++) {
            subSums[i] = BigInteger.ZERO;
        }
        return subSums;
    }
}