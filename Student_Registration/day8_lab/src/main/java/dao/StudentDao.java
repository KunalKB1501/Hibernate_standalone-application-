package dao;
import java.time.LocalDate;
import java.util.List;

import pojos.Course;
import pojos.Student;

public interface StudentDao {
	String insertStudentDetails(Student s);
	String login(String email,String pass);
	List <Student> getCourseDetails(Course course);
	String updateCourse(int empid,Course course);
	List<Student> getAllStudents();
	String updateAllDate(Course c1,LocalDate d1);
	String deleteDetails(int empid);
	String deletefromCourse(Course c);
	

}
