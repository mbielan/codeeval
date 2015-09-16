package moderate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RoboAndRobitta {

    private static class Field {
        private static final int[] VECTOR = {1, 0 , -1, 0};

        int width, height;
        int endX, endY;
        int posX, posY;
        int vectorX = 0, vectorY = 1;

        public Field(String width, String height, String endX, String endY) {
            this.width = Integer.valueOf(width);
            this.height = Integer.valueOf(height);
            this.endX = Integer.valueOf(endX);
            this.endY = Integer.valueOf(endY);
            this.posX = 1;
            this.posY = this.height;
        }

        public void makeTurn() {
            vectorX = (vectorX + 1) % 4;
            vectorY = (vectorY + 1) % 4;
            switch (vectorX) {
                case 1: height--; break;
                case 2: width--; break;
                case 3: height--; posY--; endY--; break;
                case 0: width--; posX--; endX--; break;
            }
        }

        public void makeMove() {
            posX += VECTOR[vectorX];
            posY += VECTOR[vectorY];
        }

        public boolean isEnd() {
            return posX == endX && posY == endY;
        }

        public boolean needToTurn() {
            int tmpPosX = posX + VECTOR[vectorX];
            int tmpPosY = posY + VECTOR[vectorY];
            return tmpPosX > width || tmpPosX < 1 || tmpPosY > height || tmpPosY < 1;
        }
    }

    public static void main (String[] args) throws IOException {
        try (
                BufferedReader buffer = new BufferedReader(new FileReader(args[0]))
        ) {
            String line;
            while ((line = buffer.readLine()) != null) {
                Field field = getFieldSettings(line);
                System.out.println(getNutsCount(field));
            }
        }
    }

    private static int getNutsCount(Field field) {
        int nutsCount = 0;
        while (!field.isEnd()) {
            if (field.needToTurn()) {
                field.makeTurn();
            }
            field.makeMove();
            nutsCount++;
        }
        return nutsCount + 1;
    }

    private static Field getFieldSettings(String line) {
        String[] input = line.split("\\|");
        String[] field = input[0].trim().split("x");
        String[] end = input[1].trim().split(" ");
        return new Field(field[0], field[1], end[0], end[1]);
    }
}
