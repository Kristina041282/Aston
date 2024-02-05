import java.util.concurrent.*;

public class Loader {

    public static void main(String[] args) throws Exception {

        int countThreadPool = 6;
        int lengthTotalNumberOfRegion = 99;
        int rangeSize = lengthTotalNumberOfRegion / countThreadPool;
        ExecutorService service = Executors.newFixedThreadPool(countThreadPool);
        for (int i = 0; i <= countThreadPool - 1; i++) {
            int rangeStart = rangeSize * i + 1;
            int rangeEnd = rangeSize *  (i + 1) + 1;
            if (rangeEnd + rangeSize > lengthTotalNumberOfRegion){
                rangeEnd = lengthTotalNumberOfRegion;
            }
            System.out.println("thread = " + i + ", start = " + rangeStart + ", end = " + rangeEnd); //Вывод диапазонов регионов для каждого потока
            Future<Long> submit = service.submit(new MyCallable("CarNumberGenerator/res/numbers" + i + ".txt", rangeStart, rangeEnd)); //Передаем в генератор номеров диапазон регионов
        }
        service.shutdown();
    }
}

