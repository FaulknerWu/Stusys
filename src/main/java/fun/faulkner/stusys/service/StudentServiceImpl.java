package fun.faulkner.stusys.service;

import fun.faulkner.stusys.dao.StudentDao;
import fun.faulkner.stusys.dao.StudentDaoImpl;
import fun.faulkner.stusys.entity.Student;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentDaoImpl studentDao;

    public StudentServiceImpl(StudentDaoImpl studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public Student getStudentById(int id) {
        return studentDao.getById(id);
    }

    @Override
    public Student getStudentByStudentId(String studentId) {
        return studentDao.getByStudentId(studentId);
    }

    @Override
    public boolean addStudent(String studentId, String className, String name, int age, String gender, String phone) {
        return studentDao.addStudent(studentId, className, name, age, gender, phone);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(Student student) {
        return studentDao.deleteStudent(student);
    }
}