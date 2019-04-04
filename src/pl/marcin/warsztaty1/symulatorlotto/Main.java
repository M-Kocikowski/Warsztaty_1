package pl.marcin.warsztaty1.symulatorlotto;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        System.out.println("Typowanie:");
        System.out.println("==========");
        int[] typed = typedNumbers();
        System.out.println("Wytypowane liczby to: " + Arrays.toString(typed));

        System.out.println("Losowanie");
        System.out.println("=========");
        int[] picked = pickedNumbers();
        System.out.println("Wylosowane liczby to: " + Arrays.toString(picked));

        int match = evaluateNumbers(typed, picked);
        System.out.println("W dzisiejszym losowaniu trafiłeś " + match + " z 6 liczb");
        if (match >= 3) System.out.println("Gratulacje! Wygrałeś");
        else System.out.println("Niestety nie wygrałeś");

    }

    // wylosowanie 6 liczb z puli 49
    private static int[] pickedNumbers() {

        int[] arr = new int[6];
        Random random = new Random();
        int number;

        for (int i = 0; i < arr.length; i++) {
            do {
                number = random.nextInt(49) + 1;
            } while (numberExists(number, arr));
            arr[i] = number;
        }
        Arrays.sort(arr);
        return arr;
    }

    // metoda służąca do sprawdzenia czy wylosowana/wytypowana liczba nie została już wcześniej wybrana
    private static boolean numberExists(int number, int[] arr) {

        for (int n : arr) {
            if (n == number) return true;
        }
        return false;
    }

    // wytypowanie 6 liczb przez użytkownika
    private static int[] typedNumbers(){

        System.out.println("Wybierz 6 liczb z zakresu 1 - 49:");
        Scanner scanner = new Scanner(System.in);
        int typedNumber;
        boolean numberExists;
        int[] arr = new int[6];

        for (int i = 0; i < arr.length; i++){
            do {

                System.out.println(String.format("Wpisz liczbę nr %d", i + 1));
                while (!scanner.hasNextInt()){
                    scanner.next();
                    System.out.println("To nie jest liczba");
                    System.out.println(String.format("Wpisz liczbę nr %d", i + 1));
                }
                typedNumber = scanner.nextInt();
                if (typedNumber < 1 || typedNumber > 49) System.out.println(typedNumber + " nie jest z zakresu 1 - 49");
                numberExists = numberExists(typedNumber, arr);
                if (numberExists) System.out.println("Liczba " + typedNumber + " została już wybrana.");

            }while (typedNumber < 1 || typedNumber > 49 || numberExists);

            arr[i] = typedNumber;
        }
        Arrays.sort(arr);
        return arr;
    }

    private static int evaluateNumbers(int[] typedArray, int[] pickedArray){

        int count = 0;
        for (int i : typedArray){
            for (int j: pickedArray){
                if (i == j) count++;
            }
        }
        return count;
    }

}
