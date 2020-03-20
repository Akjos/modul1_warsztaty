package guess_number_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startApp();
    }

    private static void startApp() {
        Scanner scanner = new Scanner(System.in);
        int max = 1000;
        int min = 0;
        String textFlag = "";
        System.out.println("Pomyśl liczbę od 0 do 1000, a ja ją zgadnę w max. 10 próbach");
        System.out.println("Wciśnij Enter kiedy będziesz gotowy");
        scanner.nextLine();
        for (int i = 0; i <= 10; i++) {
            int guess = ((max - min) / 2) + min;
            System.out.println("Zgaduję: " + guess);
            textFlag = scanner.nextLine();
            if (textFlag.equals("trafiłeś")) {
                System.out.println("Wygrałem w " + (i + 1) + " próbach");
                break;
            } else if (textFlag.equals("mniej")) {
                max = guess;
            } else if (textFlag.equals("więcej")) {
                min = guess;
            } else {
                System.out.println("Podaj mniej, więcej albo trafiłeś");
                i--;
            }
            if (i == 9) {
                System.out.println("Nie oszukuj!! Gramy od nowa!");
                i = -1;
                max = 1000;
                min = 0;
            }
        }
        scanner.close();
    }
}
