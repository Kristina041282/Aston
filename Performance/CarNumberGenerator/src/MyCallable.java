import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Long> {

    private String filename;
    private Long num;

    public MyCallable(String filename, Long num) {//создала конструктор для разделения задачи на несколько потоков
        this.filename = filename;//для этого инициализирую путь к файлу для записи номеров
        this.num = num;
    }

    @Override
    public Long call() throws Exception {
        long start = System.currentTimeMillis();
        PrintWriter writer = new PrintWriter(filename);

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        int regionCode;
        for (regionCode = 1; regionCode < 100; regionCode ++) {
            StringBuilder builder = new StringBuilder();//создаем StringBuilder() для каждого региона
            for (int number = 1; number < 1000; number++) {//так перебираются буквы
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCode, 2));
                            builder.append('\n');
                        }
                    }
                }
            }
            writer.write(builder.toString());//и в конце когда создали для региона набор номеров, мы запишем их сразу в файл
        }
        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
        //return builder.toString();
        return Thread.currentThread().getId();
    }


    private static String padNumber(int number, int numberLength) {//передаем на вход номер и какой длины должен возвращать числа
        String numberStr = Integer.toString(number);//создали строку, приравняли к ней номер переданный в параметры (а почему перевели в строку, потому что потом
        //номер формируется в строке в майне)
        int padSize = numberLength - numberStr.length();//отнимаем от положенной длины длину номера (int number которую передали на вход)
        for (int i = 0; i < padSize; i++) {//идем циклом до этой полученной цифры int padSize
            numberStr = numberStr.concat(String.valueOf('0'));//так у нас идет 10, 20, 30 и т.д. и затем 11, 12, 13, 14, 15 и т.д
        }
        return numberStr;//возвращаем строку
    }
}
