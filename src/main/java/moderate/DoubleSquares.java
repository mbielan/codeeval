package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public class DoubleSquares {

    private static TreeSet<Integer> squares;

    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line = buffer.readLine();
        int elemsCount = Integer.valueOf(line);
        initSquares();
        while (elemsCount-- > 0 && (line = buffer.readLine()) != null) {
            int number = Integer.valueOf(line);
            System.out.println(getDoubleSquaresCount(number));
        }
    }

    private static void initSquares() {
        int max = (int) Math.sqrt(Integer.MAX_VALUE) + 1;
        squares = new TreeSet<>();
        for (int i = 0; i < max; i++) {
            squares.add(i * i);
        }
    }

    private static int getDoubleSquaresCount(int number) {
        int doubleSquaresCount = 0;
        Iterator<Integer> squaresIter = squares.iterator();
        int square, residue;
        int limit = number / 2 + 1;
        while (squaresIter.hasNext() && (square = squaresIter.next()) < limit) {
            residue = number - square;
            if (squares.contains(residue)) {
                doubleSquaresCount++;
            }
        }
        return doubleSquaresCount;
    }
}