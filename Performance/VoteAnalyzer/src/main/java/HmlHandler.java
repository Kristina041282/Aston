import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class HmlHandler extends DefaultHandler {//этот класс будет делать: когда ему встречается на пути какой-то тег , он вызывает метод startElement, когда тег завершается,
    //он вызывает endElement

    private Connection connection;
    public StringBuilder insertQuery = new StringBuilder();


    public HmlHandler(Connection connection) {//здесь создадим этот конструктор в котором создадим этот HashMap
        this.connection = connection;
    }


    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                String birthDay = attributes.getValue("birthDay");//чтобы д.р. спарсить нужно формат д.р.
                birthDay = birthDay.replace('.', '-');
                insertQuery.append((insertQuery.length() == 0 ? "" : ",") + " ('" + attributes.getValue("name") + "', '" + birthDay + "', 1)");

                if (insertQuery.length() > 5_000_000) {
                    String sql = "INSERT INTO voter_count(name, birthDate, `count`) VALUES" + insertQuery.toString();
                    connection.createStatement().execute(sql);
                    insertQuery.delete(0, insertQuery.length());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voters")) {
            if (insertQuery.length() > 0) {
                try {
                    String sql = "INSERT INTO voter_count(name, birthDate, `count`) VALUES" + insertQuery.toString();
                    connection.createStatement().execute(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                insertQuery.delete(0, insertQuery.length());
            }
        }
    }
}
