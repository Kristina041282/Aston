import java.util.concurrent.ForkJoinPool;

public class Main {
    public static String URL = "https://www.koolinar.ru";

    public static void main(String[] args) {

        //Node node = new Node("hy");
        //System.out.println(node.getLinc());

        MyAction myAction = new MyAction(URL);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(myAction);
        //Void strings = new ForkJoinPool().invoke(myAction);//вызываем ForkJoinPool() у него метод invoke и вызываем с помощью invoke у него задачу от корневого узла.
        //Этот метод нам вернет всю кол-цию по всему нашему дереву
        System.out.println("Strings" + pool);




 //       ForkJoinPool pool = new ForkJoinPool();
 //       Node root = new Node(MyAction.URL);
 //       pool.invoke(root);
        //pool.shutdown();
        //System.out.println(" Finish");
        //System.out.println(filteredLinks.size());
    }
}




















