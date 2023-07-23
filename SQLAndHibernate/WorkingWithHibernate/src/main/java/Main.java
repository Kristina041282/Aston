import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Course course;

        //Подключаемся к б.д. для этого создаем StandardServiceRegistry вызываем у него метод configure и передаем путь
        //рекомендуется один раз статически это сделать и потом этот SessionFactory где-то получать (например в отдел.классе)
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();//получаем SessionFactory
        Session session = sessionFactory.openSession();//теперь нужно получить Session (метод openSession() позволяет его получить)
        Transaction transaction = session.beginTransaction();//и вызываем у session -> beginTransaction()
        //когда нужно делать сложные запросы нужно для этого создать CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);//затем создаем CriteriaQuery, в дженерики указываем класс и вписываем в параметры
        // класс, что хотим получать
        Root<PurchaseList> root = query.from(PurchaseList.class);//нас интересует в данном случае селект from пишем в параметры какой класс
        query.select(root);//формируем запрос, допустим хотим селект из этой таб., то есть таб. Course
        List<PurchaseList> courseList = session.createQuery(query).getResultList();//создаем createQuery(вот этого критерия query) и можем получить в разных видах результаты,
        //мы выбрали вернуть их нам списком и создали List в который все сложим
        for (PurchaseList purchaseList : courseList) {
            //LinkedPurchaseList list = new LinkedPurchaseList(purchaseList.getStudent().getId(), purchaseList.getCourse().getId());
            LinkedPurchaseList list = new LinkedPurchaseList();
            System.out.println(list.getStudentId() + " --- " + list.getCourseId());
            list.setStudentId(purchaseList.getStudent().getId());
            list.setCourseId(purchaseList.getCourse().getId());
            System.out.println("IdСтудента:" + list.getStudentId() + " - " + "IdКурса:" + list.getCourseId());
            session.persist(list);
        }
        transaction.commit();//делаем коммит (получается, закрыли транзакцию)
        sessionFactory.close();//в конце обязательно закрываем
    }
}
