import java.util.concurrent.*;

public class Loader {

    public static void main(String[] args) throws Exception {

        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 3; i++) {
            Future<Long> submit1 = service.submit(new MyCallable("CarNumberGenerator/res/numbers" + i + ".txt", ));
        }
        service.shutdown();
    }
}