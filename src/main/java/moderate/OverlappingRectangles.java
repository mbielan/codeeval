package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OverlappingRectangles {

    private static class Rectangle {
        int x1;
        int y1;
        int x2;
        int y2;

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        int getWidth() {
            return Math.abs(x2 - x1);
        }

        int getHeight() {
            return Math.abs(y2 - y1);
        }
    }

    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            String[] coordinates = line.split(",");
            Rectangle a = getRectangle(coordinates, 0);
            Rectangle b = getRectangle(coordinates, 4);
            if (intersects(a, b)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }

    private static Rectangle getRectangle(String[] coordinates, int i) {
        int x1 = Integer.valueOf(coordinates[i]);
        int y1 = Integer.valueOf(coordinates[i + 1]);
        int x2 = Integer.valueOf(coordinates[i + 2]);
        int y2 = Integer.valueOf(coordinates[i + 3]);
        return new Rectangle(x1, y1, x2, y2);
    }

    public static boolean intersects(Rectangle r1, Rectangle r2) {
        return intersectsOnX(r1, r2) && intersectsOnY(r1, r2);
    }

    public static boolean intersectsOnX(Rectangle r1, Rectangle r2) {
        if (r1.x1 < r2.x1) {
            return intersects(r1.x1, r1.getWidth(), r2.x1, r2.getWidth());
        }
        return intersects(r2.x1, r2.getWidth(), r1.x1, r1.getWidth());
    }

    public static boolean intersectsOnY(Rectangle r1, Rectangle r2) {
        if (r1.y2 < r2.y2) {
            return intersects(r1.y2, r1.getHeight(), r2.y2, r2.getHeight());
        }
        return intersects(r2.y2, r2.getHeight(), r1.y2, r1.getHeight());
    }

    private static boolean intersects(int p1, int length1, int p2, int length2) {
        return p1 + length1 + length2 >= p2 + length2;
    }
}