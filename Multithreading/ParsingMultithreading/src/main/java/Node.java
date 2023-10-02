import java.util.Set;
import java.util.concurrent.ForkJoinTask;

public class Node {

    public String link;


    public Node(String link) {//передаю в конструктор ссылку на https://www.skillbox.ru
        this.link = link;//инициализировала ссылку
    }


    public void setLink(String link) {
        this.link = link;
    }

    public String getLinc() {
        return link;
    }

    @Override
    public String toString() {
        return "Node{" +
                "link='" + link + '\'' +
                '}';
    }
}
