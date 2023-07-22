import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Purchaselist")
public class PurchaseList {//список покупок

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Student student;//чтобы можно было получать объект класса Student


    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Course course;//чтобы можно было получать объект класса Course

    //@Column(name = "student_name")
    //private String studentName;

    //@Column(name = "course_name")
    //private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    //@ManyToMany(cascade = CascadeType.ALL)
    //@JoinTable(name = "Linkedpurchaselist",
    //        joinColumns = {@JoinColumn(name = "student_id")},
    //        inverseJoinColumns = {@JoinColumn(name = "course_id")})
    //private List<Student> studentList;


    //@ManyToMany(cascade = CascadeType.ALL)
    //@JoinTable(name = "Linkedpurchaselist",
    //        joinColumns = {@JoinColumn(name = "student_id")},
    //        inverseJoinColumns = {@JoinColumn(name = "course_id")})
    //private List<Course> courseList;


    //public List<Course> getCourseList() {
    //    return courseList;
    //}

    //public void setCourseList(List<Course> courseList) {
    //    this.courseList = courseList;
    //}

    //public List<Student> getStudentList() {
    //    return studentList;
    //}

    //public void setStudentList(List<Student> studentList) {
    //    this.studentList = studentList;
    //}

    //public String getStudentName() {
    //    return studentName;
    //}

    //public void setStudentName(String studentName) {
    //    this.studentName = studentName;
    //}

    //public String getCourseName() {
    //    return courseName;
    //}

    //public void setCourseName(String courseName) {
    //    this.courseName = courseName;
    //}

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return "PurchaseList{" +
                "student=" + student +
                ", course=" + course +
                ", price=" + price +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }
}
