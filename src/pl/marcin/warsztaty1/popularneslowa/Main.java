package pl.marcin.warsztaty1.popularneslowa;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String foundWordsFile = "popular_words.txt";
        String portalURL = "http://www.onet.pl/";
        String htmlTag = "span.title";
        String filteredWordsFile = "filtered_popular_words.txt";
        String[] excludedWords = {"oraz", "ponieważ", "albo", "tak", "nie", "się", "ale", "lecz"};

        if (popularWords(foundWordsFile, portalURL, htmlTag)) {
            System.out.println("File - " + foundWordsFile + " - from website " + portalURL + " successfully created.");
            if (filteredWords(foundWordsFile, excludedWords, filteredWordsFile)) {
                System.out.println("Filtered words file - " + filteredWordsFile + " - successfully created.");
            }

        }


    }

    //wyciągnięcie wszystkich co najmniej 3 literowych słów ze wszystkich wybranych elementów występujących
    //na przekazanej stronie.
    //do metody przekazujemy nazwę pliku do którego zapiszemy słowa,
    //adres strony, oraz tag html z których chcemy wyciągać słowa
    private static boolean popularWords(String fileName, String url, String element) {

        Connection connect = Jsoup.connect(url);
        Path path = Paths.get(fileName);
        ArrayList<String> list = new ArrayList<>();

        try {

            if (!Files.exists(path)) Files.createFile(path);
            String[] tab;
            Document document = connect.get();
            Elements links = document.select(element);
            for (Element elem : links) {

                tab = elem.text().split(" ");
                for (String s : tab) {
                    if (s.length() >= 3) list.add(s);
                }

            }
            Files.write(path, list);
            return true;

        } catch (
                IOException e) {
            System.out.println("ERROR in popularWords method.");
        }
        return false;
    }

    // wyciągnięcie wszystkich słów z zapisanego wcześniej pliku
    // i przefiltrowanie pod kątem przekazanej tablicy słów wykluczonych
    private static boolean filteredWords(String fileName, String[] excludedWords, String newFileName) {

        Path path = Paths.get(fileName);
        Path newPath = Paths.get(newFileName);

        if (!Files.exists(path)) {
            System.out.println("File " + fileName + " doesn't exist!");
            return false;
        }

        List<String> list;
        ArrayList<String> newList = new ArrayList<>();
        try {

            list = Files.readAllLines(path);
            for (String s : list) {
                if (!checkWord(s, excludedWords)) newList.add(s);
            }

            if (!Files.exists(newPath)) Files.createFile(newPath);
            Files.write(newPath, newList);
            return true;

        } catch (IOException e) {
            System.out.println("ERROR in filteredWords method.");
        }
        return false;
    }

    // metoda sprawdzająca, czy słowo występuje w tablicy
    private static boolean checkWord(String word, String[] words) {

        for (String s : words) {
            if (word.equals(s)) return true;
        }
        return false;
    }
}
