import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    private static int newWidth = 300;//задаем новую ширину этим файлам, она будет везде равна 300

    public static void main(String[] args) {

        String srcFolder = "C:/Users/user/Desktop/fon";//приход
        String dstFolder = "C:/Users/user/Desktop/finish";
        String aFolder = "C:/Users/user/Desktop/finish1";
        String bFolder = "C:/Users/user/Desktop/finish2";
        String cFolder = "C:/Users/user/Desktop/finish3";
        String dFolder = "C:/Users/user/Desktop/finish4";
        String fFolder = "C:/Users/user/Desktop/finish5";


        File srcDir = new File(srcFolder);//который берет исходную папку
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();//получает из нее файлы
        int processorCores = files.length / 6;//делим этот массив на столько частей, сколько ядер у процессора, чтобы создать 6 параллельных потока

        File[] files1 = new File[processorCores];//создаем массив такой длины
        System.arraycopy(files, 0, files1, 0, files1.length);//теперь нужно скопировать(в 1-м массиве берем от 0, дальше массив в который мы это пишем
        //там тоже пишем с 0 и длина равна files1.length
        ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);//создаем наши потоки и сюда зададим соответственно files1 ширина
        // у нас задана в константе, папочка у нас есть куда мы будем это все переносить
        new  Thread(resizer1).start();//и запускаем методом start


        File[] files2 = new File[2];//и создадим массив files2 с новой длиной (берем длину исходного массива и вычитаем из него длину половинки)
        System.arraycopy(files, processorCores, files2, 0, files2.length);//теперь копируем во 2-й массив, значит опять берем исходный массив, далее исходную позицию,
        //далее массив в который мы все это копируем там мы копируем с 0 и длина files1.length которую мы уже создали
        ImageResizer resizer2 = new ImageResizer(files2,  newWidth, aFolder, start);//files2, новая ширина, папка куда мы это пишем
        new  Thread(resizer2).start();//и запускаем методом start


        new Thread(()-> {
                File[] files3 = new File[3];
                System.arraycopy(files, 7, files3, 0, files3.length);
               ImageResizer resizer3 = new ImageResizer(files3, newWidth, bFolder, start);
                new  Thread(resizer3).start();
        }).start();

        new Thread(()-> {
                    File[] files4 = new File[4];
                    System.arraycopy(files, 5, files4, 0, files4.length);
                    ImageResizer resizer4 = new ImageResizer(files4, newWidth, cFolder, start);
                    new  Thread(resizer4).start();
                }).start();

                new Thread(()-> {
                    File[] files5 = new File[5];
                    System.arraycopy(files, 0, files5, 0, files5.length);
                    ImageResizer resizer5 = new ImageResizer(files5, newWidth, dFolder, start);
                    new  Thread(resizer5).start();
                }).start();


                new Thread(()-> {
                    File[] files6 = new File[3];
                    System.arraycopy(files, 2, files6, 0, files6.length);
                    ImageResizer resizer6 = new ImageResizer(files6, newWidth, fFolder, start);
                    new  Thread(resizer6).start();
                }).start();

                //ImageResizer.resize();



    }
}