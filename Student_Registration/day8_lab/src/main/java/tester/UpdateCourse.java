package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.Course;

public class UpdateCourse {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory();Scanner sc =new Scanner(System.in)) // static method ---> cls loading ---> static init block --> singleton
												// instance of SF
		{
			System.out.println("enter the Student id :");
			int id=sc.nextInt();
			for (Course c:Course.values()) {
				System.out.println(c);
			}
			System.out.println("update Course :");
			String course=sc.next().toUpperCase();
			System.out.println("course list :");
			StudentDaoImpl dao=new StudentDaoImpl();
			String s1=dao.updateCourse(id,Course.valueOf(course));
			System.out.println(s1);
			//System.out.println("Hibernate is up n running !!!"+sf);//sf imple cls instance
		}  //sf.close() --> clean up DB connection pool!
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
