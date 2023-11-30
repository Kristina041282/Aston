import java.sql.*;

public class DBConnection {//в этом классе будем добавлять избирателей в базу данных

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "yurii2123041407kristina";

    public static Connection getConnection() {//соединяемся с базой и создает табличку с полями name birthDate и count
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName + "?user=" + dbUser + "&password=" + dbPass);//url для подключения к локальному mysql серверу. Обычно 1 приложение
                //использует 1 б.д. удобно указать здесь при подключение
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT NOT NULL, " +
                    "PRIMARY KEY(id))");//, " +
                        //"UNIQUE KEY name_date(name(50), birthDate))");//чтобы эти 2 поля были уникальным индексом мы создали здесь этот индекс "UNIQUE KEY name_date(name(50), birthDate))"
                // и обязательно указываем длину, которая будет использоваться для создания ключа
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
