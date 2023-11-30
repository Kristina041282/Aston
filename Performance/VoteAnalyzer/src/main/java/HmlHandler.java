import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class HmlHandler extends DefaultHandler {//этот класс будет делать: когда ему встречается на пути какой-то тег , он вызывает метод startElement, когда тег завершается, он вызывает
    //endElement

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");//формат даты рождения
    private static SimpleDateFormat birthDayFormat1 = new SimpleDateFormat("yyyy-MM-dd");//формат даты рождения
    private Voter voter;//создали объект Voter класса Voter
    private Connection connection;
    public StringBuilder insertQuery = new StringBuilder();

    private static HashMap<Voter, Integer> voterCounts;//посчитаем voter которые проголосовали несколько раз, создадим для этого HashMap (такой метод хранения не удобен, потому что
    //когда-нибудь он переполнится и будет вылетать ошибка, поэтому лучше использовать другое хранилище например базу данных).


    public HmlHandler (Connection connection) {//здесь создадим этот конструктор в котором создадим этот HashMap
        voterCounts = new HashMap<>();//и теперь будем сюда складывать voter когда у нас встречается visit (посещение избирательного участка)
        this.connection = connection;

    }

       public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {//этот метод срабатывает, когда парсер натыкается на какой-то элемент
           //(пример открывается 1 тег и читается в нем другой тег, затем вызывается метод endElement и в нем закрывается сперва дочерний тег, а затем и корневой)
           //и в общем когда читаем дочерний, мы должны знать какой корневой у нее был
           try {
               if (qName.equals("voter") && voter == null) {//если сталкиваемся с тегом "voter" и voter == null
                   Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));//чтобы д.р. спарсить нужно формат д.р.
                   insertQuery.append((insertQuery.length() == 0 ? "" : ",") + " ('" + attributes.getValue("name") + "', '" + birthDayFormat1.format(birthDay) + "', 1)");
                   voter = new Voter(attributes.getValue("name"), birthDay);//то будем создавать этого "voter" и будем получать attributes этого тега и передавать имя и д.р.
                   //передаем ему для создания объекта voter
               } else if (qName.equals("visit") && voter != null) {//если сталкиваемся с тегом "visit" и voter != null
                   int count = voterCounts.getOrDefault(voter, 0);//getOrDefault берет значение по ключу, но если его нет, то подставляет дефолтное значение
                   //(ключ у нас здесь voter а значение поставим 0)
                   if (voterCounts.size() > 1000) {
                       String sql = "INSERT INTO voter_count(name, birthDate, `count`) VALUES" + insertQuery.toString();
                       connection.createStatement().execute(sql);
                       voterCounts.clear();
                   }
                   voterCounts.put(voter, count + 1);//будем класть новое значение для voter и count + 1
               }
           } catch (ParseException | SQLException ex) {//здесь в этом месте можно генерировать ошибку throw new ParseException("")
               ex.printStackTrace();
           }
       }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {//когда вызывается этот метод, значит "voter" наш закрылся
            voter = null;//и мы его можем стереть
        }
    }

    public HashMap<Voter, Integer> getVoterCounts() {
        return voterCounts;
    }
}
