import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Loader {

    private static String fileName = "VoteAnalyzer/res/data-1M.xml";

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();

    public static void main(String[] args) throws Exception {


        ////* создали SAXParserFactory для записи в список (связка с классом HmlHandler)
        SAXParserFactory factory = SAXParserFactory.newInstance();//для того, чтобы его запустить
        SAXParser parser = factory.newSAXParser();//создаем SAXParser
        HmlHandler handler = new HmlHandler(DBConnection.getConnection());//дальше наш класс HmlHandler
        long start = System.currentTimeMillis();//проверим за какое время происходит парсинг файла
        parser.parse(new File(fileName), handler);//вызываем у parser метод parse и передаем наш исходный файл и handler
        System.out.println("Parsing duration" + " " + (System.currentTimeMillis() - start) + "ms");
        //System.out.println();
        //handler.printDuplicatedVoters();
        //*
    }
}