import jakarta.persistence.*;
@Entity
@Table(name = "Linkedpurchaselist")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseListKey id;//в качестве id указали экземпляр класса ключа LinkedPurchaseListKey

    //Если поля ключа использовать и в основном классе @Entity, то необходимо запретить использование полей для вставки
    // и обновления данных дополнительными параметрами insertable и updatable в аннотации @Column. (Как сделала ниже)
    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;
    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    public LinkedPurchaseList() {
    }

    public LinkedPurchaseListKey getId() {
        return id;
    }

    public void setId(LinkedPurchaseListKey id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "LinkedPurchaseList{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                '}';
    }


}


