package dao;

import java.util.List;

import pojos.Course;
import pojos.Student;

public interface StudentDao {
	String insertStudentDetails(Student s);
	String login(String email,String pass);
	List <Student> getCourseDetails(Course course);
	String updateCourse(int empid,Course course);
	List<Student> getAllStudents();
	

}
