package dice;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        startApp();
    }

    private static void startApp() {
        System.out.println(getRoll("3D20+10"));
        System.out.println(getRoll("36+12110"));
        System.out.println(getRoll("D100-10"));
        System.out.println(getRoll("2D106"));
    }

    private static String getRoll(String stringDice) {
        int indexD = getDIndex(stringDice);
        if (indexD < 0) {
            return "Niepoprawny wzór rzutu";
        }
        int numberOfRoll = getNumberOfRoll(stringDice, indexD);
        int charIndex = getCharIndex(stringDice);
        String roll = rollDice(stringDice, indexD, numberOfRoll, charIndex);
        return roll;
    }

    private static String rollDice(String stringDice, int indexD, int numberOfRoll, int charIndex) {
        Random random = new Random();
        int dice, modifier, roll = 0;
        if (charIndex > 0) {
            dice = Integer.valueOf(stringDice.substring(indexD + 1, charIndex));
        } else {
            dice = Integer.valueOf(stringDice.substring(indexD + 1));
        }
        if (dice == 3 || dice == 4 || dice == 6 || dice == 8 || dice == 12 || dice == 20 || dice == 100) {
            if (charIndex > -0) {
                modifier = Integer.valueOf(stringDice.substring(charIndex));
            } else {
                modifier = 0;
            }
            for (int i = 0; i < numberOfRoll; i++) {
                roll += random.nextInt(dice) + 1;
            }
            roll += modifier;
            return Integer.toString(roll);
        } else {
            return "Nie mamy takiej kostki";
        }
    }

    private static int getCharIndex(String stringDice) {
        int charIndex = stringDice.indexOf('+');
        if (charIndex > 0) {
            return charIndex;
        } else {
            charIndex = stringDice.indexOf('-');
        }
        if (charIndex > 0) {
            return charIndex;
        }
        return 0;
    }

    private static int getNumberOfRoll(String stringDice, int indexD) {
        if (indexD == 0) {
            return 1;
        } else {
            String str = stringDice.substring(0, indexD);
            return Integer.valueOf(str);
        }
    }

    private static int getDIndex(String stringDice) {
        int index = stringDice.indexOf('D');
        return index;

    }

}
