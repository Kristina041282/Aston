import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class MyAction extends RecursiveAction {

    public Node node;
    public static Set<String> linksFromTags = new HashSet<>();
    public static Set<Node> filteredLinks = new HashSet<>();//создала Set для того, чтобы сложить сюда все ссылки
    public List<MyAction> taskLists = new ArrayList<>();
    public String string;

 //   public MyAction(Node node) {
 //       this.node = node;
 //   }
    public MyAction(String string) {//передаю в параметры String URL
        this.string = string;//инициализирую ссылку
    }


    @Override
    protected void compute() {

        try {
            TimeUnit.MILLISECONDS.sleep(150);
            Document document = Jsoup.connect(Main.URL).get();//выкачиваем содержимое текста
            Elements elements = document.select("a");//выводим все гиперссылки которые находятся на странице

            elements.forEach(element -> {
                linksFromTags.add(element.attr("href"));//и чтобы вывести сам url, который содержится в ссылке, для этого обращаемся к аттрибуту этого элемента
            });//гиперссылки указываются в параметре href (и мы получаем конкретные ссылки из этих тегов)

            linksFromTags.forEach(s -> {//далее прохожу по каждой этой ссылке и отфильтровываю
                if (!s.endsWith(".pdf") && !s.contains("#")) {
                node.setLink(s);//в таком случае каждую ссылку складываю в отдельный объект класса Node (для их хранения)
                MyAction task = new MyAction((node.getLinc()));//создаю объект класса MyAction и кладу в него ссылки
                task.fork();//fork() поделит эту задачу на подзадачи
                taskLists.add(task);//вложим эти подзадачи в List<MyAction>
                filteredLinks.add(node);//и так же складываю ссылки в Set<Node>
                  //////filteredLinks.add(node.getLinc());//затем их складываю в Set<Node>
                }
                filteredLinks.forEach(System.out::println);
            });

            //поскольку все подзадачи сохранили, то просто перебираем все эти задачи
            for (MyAction taskList : taskLists) {
                taskList.join();//и соберем результат работы с помощью join()
                //////     filteredLinks.addAll(taskList.join());
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }



//        Collection<Node> nodes = node.getChildren();
//                //List<MyAction> taskList = new ArrayList<>();
//                for (Node child : node.getChildren()) {
//                    MyAction task = new MyAction(child);
//                    task.fork();
//                    taskList.add(task);
//                }
//                for (MyAction task : taskList) {
//                    task.join();
//                }

    }
}









////Document document = Jsoup.connect(URL).timeout(5000).get();
//, и установили timeout() By default,Jsoup only allows working with HTML and XML
//            //content type and throws exceptions for others. So, you will need to specify this properly in order to work with other content types, such as RSS, Atom, and so on