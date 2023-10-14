import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveTask;

public class LinkFinder extends RecursiveTask<Set<String>> {

private static CopyOnWriteArrayList<String> linksFromTags = new CopyOnWriteArrayList<>();

    public String url;
    public String link = "";//Почему бы не хранить ссылку просто в переменной типа строка

    public LinkFinder(String url) {//в методе compute мы парсим все ссылки со страницы, которая была передана в конструкторе
        this.url = url.trim();//инициализирую ссылку (присваиваю значение в переменную string)
    }

    @Override
    protected Set<String> compute() {
        Set<LinkFinder> tasks = new HashSet<>();//это список запущенных
        Set<String> links = new HashSet<>();
        try {
            Thread.sleep(150);
            Document document = Jsoup.connect(url).ignoreHttpErrors(true).get();//выставила игнорирование ошибок
            Elements elements = document.select("a");

//            if ((!url.isEmpty() && !linksFromTags.contains(url) && !url.contains("#"))) {
//                linksFromTags.add(link);
//            }

            elements.forEach(el -> {
                String item = el.attr("abs:href");
                if ((!url.isEmpty() && !linksFromTags.contains(url) && !url.contains("#"))) {
                    linksFromTags.add(item);
                    for (String s : linksFromTags) {
                        LinkFinder task = new LinkFinder(item);//создаём новый экземпляр RecursiveAction, передавая в конструктор эту ссылку
                        task.fork();//делаем на ней fork (то есть запускаю эту задачу асинхронно)
                        tasks.add(task);//и добавляем эту задачу в список запущенных
                        //linksFromTags.add(item);//это и будет нам заменой класса Node вместо этой строки node.addChild(new Node(link));
                    }
                }
            });

            for (LinkFinder task : tasks) {//После перебора всех ссылок проходим по списку запущенных задач
                links.addAll(task.join());
                //links = task.join();//и соберем результат работы с помощью join()
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return links;
    }

    public String getUrl() {
        return url;
    }

}