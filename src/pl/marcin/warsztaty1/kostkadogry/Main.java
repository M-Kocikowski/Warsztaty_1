package pl.marcin.warsztaty1.kostkadogry;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        String dice = "2D10+2";
        int sum = throwDice(dice);
        if (sum == -1) System.out.println(dice + " - niewłaściwy typ kostki");
        else System.out.println("Wynik rzutu kostką " + dice + " to: " + sum);
    }


    private static int throwDice(String dice) {

        //sprawdzenie czy jest dobry typ kostki
        if (dice.indexOf('D') == -1) return -1;

        int x = 1, y = 0, z = 0, total = 0;
        String[] firstSplit = dice.split("D");

        try {
            x = Integer.parseInt(firstSplit[0]);
        } catch (NumberFormatException e) {
            //nie trzeba nic robić - jeden rzut kostką jest ustawiony domyślnie
        }

        String[] secondSplit;
        if (firstSplit[1].indexOf('+') != -1) {

            secondSplit = firstSplit[1].split("\\+");

            try {
                y = Integer.parseInt(secondSplit[0]);
            } catch (NumberFormatException e) {
                //nie trzeba nic robić
            }
            try {
                z = Integer.parseInt(secondSplit[1]);
            } catch (NumberFormatException e) {
                //nie trzeba nic robić
            }
        } else if (firstSplit[1].indexOf('-') != -1) {
            secondSplit = firstSplit[1].split("-");

            try {
                y = Integer.parseInt(secondSplit[0]);
            } catch (NumberFormatException e) {
                //nie trzeba nic robić
            }
            try {
                z = -Integer.parseInt(secondSplit[1]);
            } catch (NumberFormatException e) {
                //nie trzeba nic robić
            }
        } else {
            try {
                y = Integer.parseInt(firstSplit[1]);
            } catch (NumberFormatException e) {
                //nie trzeba nic robić
            }
        }

        //wykonanie każdego rzutu w zależności od wartości x
        Random random = new Random();
        for (int i = 1; i <= x; i++) {
            total += random.nextInt(y) + 1;
        }

        //przy ujemnym modyfikatorze z wynik kostką może być ujemny.
        //Zakładamy, że całkowity wynik nie będzie niższy od zera.
        //Można usunąć pierwszy zapis return -1, jeśli kostka jest niewłaściwa (nie ma 'D' w nazwie)
        //wtedy zakładamy, że kostka musi być prawdziwa, a my możemy zwrócić ujemny wynik rzutu.
        if (total + z < 0) return 0;
        else return total + z;
    }
}
