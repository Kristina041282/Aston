import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveTask;

public class LinkFinder extends RecursiveTask<Set<String>> {
//    public class LinkFinder extends RecursiveTask<String> {

    private static CopyOnWriteArrayList<String> linksFromTags = new CopyOnWriteArrayList<>();
    public String url;
    public String link = "";//Почему бы не хранить ссылку просто в переменной типа строка

    Set<String> links = new HashSet<>();

    public LinkFinder(String url) {//в методе compute мы парсим все ссылки со страницы, которая была передана в конструкторе
        this.url = url;//инициализирую ссылку (присваиваю значение в переменную string)
    }

    @Override
//    protected String compute() {
    protected Set<String> compute() {
        Set<LinkFinder> tasks = new HashSet<>();//это список запущенных
        try {
            Thread.sleep(200);
            Document document = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).get();//выставила игнорирование ошибок
            Elements elements = document.select("a[href*=/]");

            elements.forEach(el -> {
                String item = el.attr("abs:href");
                if ((!url.isEmpty() && !linksFromTags.contains(url) && !url.contains("#"))) {
                    linksFromTags.add(item);
                    for (String s : linksFromTags) {
                        LinkFinder finder = new LinkFinder(s);//создаём новый экземпляр RecursiveAction, передавая в конструктор эту ссылку
                        finder.fork();//делаем на ней fork (то есть запускаю эту задачу асинхронно)
                        tasks.add(finder);//и добавляем эту задачу в список запущенных
                    }
                }
            });

            for (LinkFinder task : tasks) {//После перебора всех ссылок проходим по списку запущенных задач
//                link = task.join();
                links.addAll(task.join());
                //links = task.join();//и соберем результат работы с помощью join()
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return links;
//        return link;
    }





    public String getUrl() {
        return url;
    }

    public static CopyOnWriteArrayList<String> getLinksFromTags() {
        return linksFromTags;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getUrl());
        getLinksFromTags().forEach(child -> result.append("\n").append(child));
        return result.toString();
    }
}