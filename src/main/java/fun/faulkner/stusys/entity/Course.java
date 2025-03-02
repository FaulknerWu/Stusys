package fun.faulkner.stusys.entity;

public class Course {
    private final int id;
    private String courseId;
    private String courseName;
    private String teacherName;
    private int credits;

    public Course(int id, String courseId, String courseName, String teacherName, int credits) {
        this.id = id;
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

}
