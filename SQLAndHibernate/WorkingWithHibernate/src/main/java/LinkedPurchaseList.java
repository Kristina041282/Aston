import jakarta.persistence.*;
@Entity

@Table(name = "Linkedpurchaselist")
public class LinkedPurchaseList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Column(name = "student_id")
    //@Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Column(name = "course_id")
    //@Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    //public LinkedPurchaseList(int studentId, int courseId) {
    //    this.studentId = studentId;
    //    this.courseId = courseId;
    //}


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
}


