import org.imgscalr.Scalr;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
//чтобы создать поток, создадим класс и отнаследуем его от Thread и переопределим в нем метод run() и напишем в нем то, что хотим,
// чтобы выполнялось в отдельном потоке

public class ImageResizer implements Runnable {

    //эти переменные создадим еще и здесь
    private File[] files;
    private int newWidth;
    private int newHeight;
    private String dstFolder;
    private long start;//создали число и передаем его в параметры, чтобы узнать за какое время все наши файлы обработаются
    private int count;
    private BufferedImage originalImage;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start) {//создали конструктор и будем передавать через него эти значения
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }


    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);//она читает этот файл с помощью класса ImageIO в BufferedImage
                if (image == null) {//если файл не прочитался, то цикл продолжается, читаем следующий файл
                    continue;
                }
                int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));//дальше рассчитывает новую высоту исходя из новой ширины
                //то есть здесь получаем округленное значение, преобразуем его в int
                BufferedImage newImage = resize(image, newWidth, newHeight);//далее создаем новую картинку, нового размера  пропуская ее через метод resize
                //BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);//дальше создаем новую картинку, нового размера 300
                // на сколько-то пикселей, задаем ей тип, что это тип int rgb (то есть каждый пиксель задается целым числом и учитывает красного, зеленого, синего компонентов)
                int widthStep = image.getWidth() / newWidth;//здесь мы берем не все пиксели, а каждый какой-то (допустим у нас было 3000 на сколько-то пикселей, а у нас
                //итоговая будет 300то мы берем каждый 10-й пиксель) получается здесь получим цифру 10
                int heightStep = image.getHeight() / newHeight;//здесь получим цифру в соответствии с высотой нашего изображения которое мы получим
                for (int x = 0; x < newWidth; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);//берем эти пиксели
                        newImage.setRGB(x, y, rgb);//и вставляем в итоговое изображение(условно каждый 10-й, 20-й пиксель...)
                    }
                }
                File newFile = new File(dstFolder + "/" + file.getName());//после этого создаем новый файл в папке dstFolder
                ImageIO.write(newImage, "jpg", newFile);//и туда его сохраняем в формате jpg
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished after start: " + (System.currentTimeMillis() - start + "ms"));
    }


    public static BufferedImage resize(BufferedImage src, int targetWidth, int targetHeight) {
        return Scalr.resize(src, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight);
    }
}















    //BufferedImage simpleResizeImage(BufferedImage src, int newWidth) throws Exception {
    //    for (File file : files) {
    //        image = ImageIO.read(file);//она читает этот файл с помощью класса ImageIO в BufferedImage
    //        if (image == null) {//если файл не прочитался, то цикл продолжается, читаем следующий файл
    //            continue;
    //        }
    //        newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));
    //        //newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

    //        File newFile = new File(dstFolder + "/" + file.getName());//после этого создаем новый файл в папке dstFolder
    //        ImageIO.write(image, "jpg", newFile);//и туда его сохраняем в формате jpg
    //    }
    //    return Scalr.resize(image, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, newWidth);
    //}


    //public static BufferedImage resize(BufferedImage image, int newWidth, int newHeight) {
    //    BufferedImage resizeImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
    //    Graphics2D graphics2D = resizeImage.createGraphics();
    //    graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);
    //    graphics2D.dispose();
    //    return resizeImage;
    //}



