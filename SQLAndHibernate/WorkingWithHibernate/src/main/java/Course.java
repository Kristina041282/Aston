import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int duration;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;//type в б.д. у нас это enum, поэтому создаем здесь enum
    private String description;

    //@Column(name = "teacher_id")
    //private int teacherId;
    //в нашем случае у нас у 1-го учителя может быть много курсов, но у каждого курса может быть только 1 учитель
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//установили связь за счет аннотации (в нашем примере много курсов на 1-го учителя)
    private Teacher teacher;//изменили так, чтобы можно было получать объект класса Teacher (и так же переделаем гет и сет), добавили аннотацию ленивая загрузка

    @Column(name = "students_count", nullable = true)//прописали что может быть = null так же установили Integer здесь и в id, потому что int не может быть = null
    private Integer studentsCount;
    private  int price;
    @Column(name = "price_per_hour")
    private  float pricePerHour;

    @ManyToMany(cascade = CascadeType.ALL)//у этой кол-ции создаем связь с ManyToMany, чтобы ее задать нужно прописать подробную инфу, какую таб. будем
    @JoinTable(name = "subscriptions",//использовать для связки этих 2-х таб.(курсы и студенты) в нашем случае это таб.subscriptions
            joinColumns = {@JoinColumn(name = "course_id")},//перечисляем те поля которые соединяем 1 колонка это course_id
            inverseJoinColumns = {@JoinColumn(name = "student_id")})// 2 колонка это student_id
    private List<Student> students;// и обязательно создаем геттеры и сеттеры

//чтобы сделать этот класс полноценным, добавили геттеры и сеттеры

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    //public int getTeacherId() {
    //    return teacherId;
    //}

    //public void setTeacherId(int teacherId) {
    //    this.teacherId = teacherId;
    //}

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}

