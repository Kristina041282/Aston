import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        Node root = new Node(MyAction.URL);//передаем ссылку
        Set<String> strings = new ForkJoinPool().invoke(root);//вызываем ForkJoinPool() у него метод invoke и вызываем с помощью invoke у него задачу от корневого узла.
        //Этот метод нам вернет всю кол-цию по всему нашему дереву
        System.out.println(strings);



 //       ForkJoinPool pool = new ForkJoinPool();
 //       Node root = new Node(MyAction.URL);
 //       pool.invoke(root);
        //pool.shutdown();
        //System.out.println(" Finish");
        //System.out.println(filteredLinks.size());
    }
}




















