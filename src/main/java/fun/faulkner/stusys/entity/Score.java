package fun.faulkner.stusys.entity;

public class Score {
    private final int id;
    private String courseId;
    private String studentId;
    private double score;

    public Score(int id, String courseId, String studentId, double score) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.score = score;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
