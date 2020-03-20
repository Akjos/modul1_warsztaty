package guess_number;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startApp();

    }

    private static void startApp() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        int number = -1;
        while (randomNumber != number) {
            System.out.println("Zgadnij liczbę");
            while (!scanner.hasNextInt()) {
                System.out.println("Podaj liczbę z zakresu 1-100");
                scanner.next();
            }
            number = scanner.nextInt();
            if (number < randomNumber) {
                System.out.println("Za mało!");
            }
            if (number > randomNumber) {
                System.out.println("Za dużo!");
            }
        }
        System.out.println("Zgadłeś!");
        scanner.close();
    }
}
