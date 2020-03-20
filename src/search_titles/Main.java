package search_titles;

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
        startApp();
    }

    private static void startApp() {
        String page1 = "http://www.onet.pl/";
        String path1 = "src/search_titles/popular_words.txt";
        String path2 = "src/search_titles/filtered_popular_words.txt";
        List<String> wordsList;
        List<String> wordsToOut = load(Paths.get(path1));
        wordsList = getTitleFromPage(page1);
        wordsList.removeAll(wordsToOut);
        saveList(Paths.get(path2), wordsList);

    }


    private static ArrayList<String> getTitleFromPage(String page) {
        Connection connect = Jsoup.connect(page);
        ArrayList<String> wordsList = new ArrayList<>();
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            for (Element elem : links) {
                wordsList.addAll(deleteSpecialChar(elem.text()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsList;
    }

    public static List<String> deleteSpecialChar(String str) {
        String[] tab = str.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 33; j < 65; j++) {
                tab[i] = tab[i].replace((String.valueOf((char) j)), "");
            }
            if (tab[i].length() > 3) {
                list.add(tab[i]);
            }
        }
        return list;
    }

    private static List<String> load(Path path) {
        List<String> list = new ArrayList<>();
        try {
            list.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    static void saveList(Path path, List<String> list) {
        try {
            Files.write(path, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
