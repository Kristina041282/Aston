import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Main {

    public static String URL = "https://skillbox.ru";
    public static Set<String> linksFromTags = new HashSet<>();
    public static Set<String> filteredLinks = new HashSet<>();

    public static void main(String[] args) {

        try {
            TimeUnit.MILLISECONDS.sleep(150);
            Document document = Jsoup.connect(URL).get();//выкачиваем содержимое текста
            Elements elements = document.select("a");//выводим все гиперссылки которые находятся на странице
            elements.forEach(element -> {
                linksFromTags.add(element.attr("href"));//и чтобы вывести сам url, который содержится в ссылке, для этого обращаемся к аттрибуту этого элемента
            });//гиперссылки указываются в параметре href (и мы получаем конкретные ссылки из этих тегов)
            linksFromTags.forEach(s -> {//далее прохожу по каждой этой ссылке и отфильтровываю
                    if (!s.endsWith(".pdf") && !s.contains("#")) {
                        filteredLinks.add(s);//отфильтрованные складываю в set1
                    }
                //}
                filteredLinks.forEach(System.out::println);
            });
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();//можно сделать не printStackTrace()а записывать этот StackTrace в logger
        }
    }
}





















//System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println("linksFromTags" + linksFromTags);
//            System.out.println();
//            System.out.println();
//            System.out.println();

        //System.out.println();
        //            System.out.println();
        //            System.out.println();
        //            System.out.println("filteredLinks" + filteredLinks);





