package moderate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class StackImplementation {
    public static void main (String[] args) throws IOException {
        try (
                BufferedReader buffer = new BufferedReader(new FileReader(args[0]))
        ) {
            String line;
            while ((line = buffer.readLine()) != null) {
                System.out.println(getAlternatesFromStack(line.split(" ")));
            }
        }
    }

    private static String getAlternatesFromStack(String[] elems) {
        Stack<String> stack = new Stack<>();
        for (String elem : elems) {
            stack.push(elem);
        }
        StringBuilder sb = new StringBuilder();
        boolean returnElem = true;
        while (!stack.isEmpty()) {
            String elem = stack.pop();
            if (returnElem) {
                sb.append(elem);
                sb.append(" ");
            }
            returnElem = !returnElem;
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
