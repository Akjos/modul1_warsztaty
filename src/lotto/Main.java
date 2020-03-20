package lotto;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startApp();
    }

    private static void startApp() {
        System.out.println("Zagrajmy w lotto");
        System.out.println("------------------------------------------------");
        int[] lottoTab = getRandomNumber();
        int[] userNumber = fillUserGuessTab();
        int numberHit = checkTabs(lottoTab, userNumber);
        System.out.println("Numery Lotto: " + Arrays.toString(lottoTab));
        System.out.println("------------------------------------------------");
        System.out.println("Twoje Numery: " + Arrays.toString(userNumber));
        System.out.println("------------------------------------------------");
        System.out.println("Trafiłeś " + numberHit + " liczb");
    }

    private static int checkTabs(int[] lottoTab, int[] userNumber) {
        int mark = 0;
        for (int number : lottoTab) {
            if (checkNumberInTab(userNumber, number)) {
                mark++;
            }
        }
        return mark;
    }

    private static int[] fillUserGuessTab() {
        Scanner scanner = new Scanner(System.in);
        int[] tab = new int[6];
        for (int i = 0; i < tab.length; ) {
            System.out.println("Podaj liczbę nr: " + (i + 1));
            int number = getNextIntNumber(scanner);
            if (number > 49 || number <= 0) {
                System.out.println("Liczba z poza zakresu 1-49");
            } else if (checkNumberInTab(tab, number)) {
                System.out.println("Nie możesz wybrać tej samej liczby");
            } else {
                tab[i] = number;
                i++;
            }
        }
        Arrays.sort(tab);
        scanner.close();
        return tab;
    }

    private static int getNextIntNumber(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("To nie jest liczba całkowita");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static int[] getRandomNumber() {
        Random random = new Random();
        int[] tab = new int[6];
        for (int i = 0; i < tab.length; ) {
            int number = random.nextInt(49) + 1;
            if (!checkNumberInTab(tab, number)) {
                tab[i] = number;
                i++;
            }
        }
        Arrays.sort(tab);
        return tab;
    }

    private static boolean checkNumberInTab(int[] tab, int number) {
        for (int element : tab) {
            if (number == element) {
                return true;
            }
        }
        return false;
    }
}
