import java.util.concurrent.ForkJoinPool;

public class Main {
    //public static String URL = "https://www.lenta.ru";
    public static String URL = "https://www.dnk.ru";

    public static void main(String[] args) {

        System.out.println("Старт" + URL);
        LinkFinder link = new LinkFinder(URL);
        System.out.println("Запущен пул");
        ForkJoinPool pool1 = new ForkJoinPool();
        pool1.invoke(link);
        System.out.println("Finish");

        System.out.println(" ");
    }
}


















