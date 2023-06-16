package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.Course;
import pojos.Student;

public class AddStudent {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory();Scanner sc=new Scanner(System.in)) // static method ---> cls loading ---> static init block --> singleton
												// instance of SF
		{
			//System.out.println("Hibernate is up n running !!!"+sf);//sf imple cls instance
		//create student dao instance to invoke method
			StudentDaoImpl dao=new StudentDaoImpl();
			System.out.println("enter Student Details :"
					+ " firstName,  lastName,  email,  password,  course,  date");
			
			Student s1=new Student(sc.next(), sc.next(), sc.next(), sc.next(), Course.valueOf(sc.next().toUpperCase()), LocalDate.parse(sc.next()));
			dao.insertStudentDetails(s1);
			System.out.println("Registered student :\n"+s1);
		}  //sf.close() --> clean up DB connection pool!
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
