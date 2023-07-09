import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";//для подключения к локальному sql-серверу
        String user = "root";
        String pass = "041407kristina";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT course_name, " +
                            "count(subscription_date) / (MAX(MONTH(subscription_date)+1) - MIN(MONTH(subscription_date))) 'average' "+
                    "FROM Purchaselist  GROUP BY course_name");//получая ResultSet мы можем оперировать построчно
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name" );//получаем такое-то поле из таблицы Purchaselist
                String  average = resultSet.getString("average");
                System.out.println(courseName + " - " + average);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
