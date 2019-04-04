package pl.marcin.warsztaty1.zgadywanieliczb2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int pickedNumber = pickedNumber(), min = 0, max = 1000, numberOfTries = 1, response;
        int typedNumber = ((max - min) / 2) + min;
        response = response(typedNumber, numberOfTries);

        while (response != 3 && numberOfTries < 10) {
            if (response == 1) max = typedNumber;
            else min = typedNumber;
            numberOfTries++;
            typedNumber = ((max - min) / 2) + min;
            response = response(typedNumber, numberOfTries);
        }

        if (numberOfTries > 10) System.out.println("Oszukiwałeś!");
        else System.out.println("Wygrałem! Zgadłem w " + numberOfTries + " próbach!");

    }

    // metoda do wczytania liczby przez użytkownika, która ma być odgadnięta
    private static int pickedNumber() {

        int pickedNumber;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Podaj liczbę z zakresu 0 - 1000, a ja zgadnę w max. 10 próbach");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("To nie jest liczba");
                System.out.println("Podaj liczbę z zakresu 0 - 1000, a ja zgadnę w max. 10 próbach");
            }
            pickedNumber = scanner.nextInt();
            if (pickedNumber < 0 || pickedNumber > 1000)
                System.out.println("Podana liczba nie jest z zakresu 0 - 1000");

        } while (pickedNumber < 0 || pickedNumber > 1000);

        return pickedNumber;
    }

    // metoda służąca do pobrania odpowiedzi od użytkownika dla próby odgadnięcia
    private static int response(int typedNumber, int numberOfTries) {

        System.out.println("Próba " + numberOfTries + ". Zgaduję: " + typedNumber);
        System.out.println("Wybierz odpowiedź:");
        System.out.println("[1] - za dużo");
        System.out.println("[2] - za mało");
        System.out.println("[3] - zgadłeś!");

        Scanner scanner = new Scanner(System.in);
        int pick;

        do {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("To nie jest liczba.");
            }
            pick = scanner.nextInt();
            if (pick < 1 || pick > 3) System.out.println("Musisz wybrać odpowiedź z zakresu 1 - 3");

        } while (pick < 1 || pick > 3);

        return pick;
    }

}
