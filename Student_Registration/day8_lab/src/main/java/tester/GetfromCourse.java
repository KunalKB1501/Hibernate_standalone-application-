package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.Course;

public class GetfromCourse {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory();Scanner sc=new Scanner(System.in)) // static method ---> cls loading ---> static init block --> singleton
												// instance of SF
		{
			StudentDaoImpl dao=new StudentDaoImpl();
			System.out.println("enter the course name from the list ");
			for(Course c:Course.values())
			{
				System.out.println(c);
			}
	System.out.println(dao.getCourseDetails(Course.valueOf(sc.next().toUpperCase())));
			
			//System.out.println("Hibernate is up n running !!!"+sf);//sf imple cls instance
		}  //sf.close() --> clean up DB connection pool!
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
