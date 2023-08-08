import java.io.File;
public class Main {

    private static int newWidth = 300;//задаем новую ширину этим файлам, она будет везде равна 300

    public static void main(String[] args) {

        String srcFolder = "C:/Users/user/Desktop/fon";//приход
        String dstFolder = "C:/Users/user/Desktop/finish";

        File srcDir = new File(srcFolder);//который берет исходную папку
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();//получает из нее файлы //Файлы списка методов внешне помечены как обнуляемые
        System.out.println("Кол-во файлов" + " " + files.length);//у нас пять картинок
        int cores = Runtime.getRuntime().availableProcessors();//получаем экземпляр класса Runtime с помощью метода getRuntime(), а затем вызываем метод availableProcessors()
        //который возвращает количество доступных ядер процессора
        System.out.println("Кол-во процессоров" + " " + cores);//шесть доступных ядер процессора
        int processorCores = (files.length / cores) + 1;//делим этот массив на столько частей, сколько ядер у процессора, чтобы создать 6 параллельных потока
        System.out.println("Длина потока" + " " + processorCores);//создаем длину потока

        int position = 0;
        for (int i = 0; i < cores; i++) {
            File[] file = new File[processorCores];//создаем массив такой-то длины
            System.arraycopy(files, position, file, 0, file.length);//будем копировать каждый поток с той позиции на которой закончили
            position += processorCores;//каждую итерацию прибавляем длину массива к точке отчета
                ImageResizer resizer = new ImageResizer(file, newWidth, dstFolder, start);
                new Thread(resizer).start();

        }
    }
}


