package fun.faulkner.stusys.entity;

public class Student {
    private final int id;
    private final String studentId;
    private String className;
    private String name;
    private int age;
    private String gender;
    private String phone;

    public Student(int id, String studentId, String className, String name, int age, String gender, String phone) {
        this.id = id;
        this.studentId = studentId;
        this.className = className;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
