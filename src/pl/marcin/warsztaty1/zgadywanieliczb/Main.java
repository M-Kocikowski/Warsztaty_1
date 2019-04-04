package pl.marcin.warsztaty1.zgadywanieliczb;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Zgadywanie liczb z zakresu 1 - 100");
        System.out.println("================");

        // Losowanie liczby
        Random random = new Random();
        int targetNumber = random.nextInt(100) + 1;

        // zgadywanie
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            System.out.println("Podaj liczbę:");
            while (!scanner.hasNextInt()){
                scanner.next();
                System.out.println("To nie jest liczba");
            }
            number = scanner.nextInt();

            if (number < targetNumber) System.out.println("Podana liczba jest za mała");
            else if (number > targetNumber) System.out.println("Podana liczba jest za duża");

        }while (number != targetNumber);

        System.out.println("Brawo! Zgadłeś!");
    }
}
