package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;

public class Logincheck {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory();Scanner sc=new Scanner(System.in)) // static method ---> cls loading ---> static init block --> singleton
												// instance of SF
		{
			StudentDaoImpl dao=new StudentDaoImpl();
			System.out.println("enter email and password");
			String result=dao.login(sc.next(), sc.next());
			System.out.println(result);
		}  //sf.close() --> clean up DB connection pool!
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
