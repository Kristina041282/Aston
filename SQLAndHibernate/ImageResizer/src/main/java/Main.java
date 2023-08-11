import java.io.File;

public class Main {

    private static int newWidth = 300;//задаем новую ширину этим файлам, она будет везде равна 300

    public static void main(String[] args) {

        String srcFolder = "C:/Users/user/Desktop/fon";//приход
        String dstFolder = "C:/Users/user/Desktop/finish";

        File srcDir = new File(srcFolder);//который берет исходную папку
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();//получает из нее файлы //Файлы списка методов внешне помечены как обнуляемые
        int cores = Runtime.getRuntime().availableProcessors();//получаем экземпляр класса Runtime с помощью метода getRuntime(), а затем вызываем метод availableProcessors()
        //который возвращает количество доступных ядер процессора
        int processorCores = files.length / cores;//длина потока (это будет длиной массива)
        int remainder = files.length % cores;//остаток

        int position = 0;
        for (int i = 0; i < cores; i++) {
            if (i == cores-1) {
                processorCores += remainder;
            }
                File[] files1 = new File[processorCores];
                System.arraycopy(files, position, files1, 0, files1.length);
                ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder, start);
                position += processorCores;
                new Thread(resizer).start();
        }
    }
}