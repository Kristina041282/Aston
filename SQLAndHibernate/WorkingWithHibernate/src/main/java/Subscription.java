import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Column(name = "student_id")
    private int studentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

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

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}

