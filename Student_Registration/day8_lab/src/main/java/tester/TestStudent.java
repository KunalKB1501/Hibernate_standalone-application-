package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.Course;
import pojos.Student;

public class TestStudent {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory();Scanner sc=new Scanner(System.in)) // static method ---> cls loading ---> static init block --> singleton
												// instance of SF
		{
			StudentDaoImpl dao=new StudentDaoImpl();
			boolean exit=true;
			System.out.println("courses List");
			for(Course c:Course.values())
			{
				System.out.println(c);
			}
			System.out.println("---------------------------------------");
			System.out.println("1.Register Student");
			System.out.println("2.Student login");
			System.out.println("3.Update Student course");
			System.out.println("4.Student details by course");
			System.out.println("5.show all student details");
			System.out.println("6.update all course date to 2023-06-12");
			System.out.println("7.delete student details");
			System.out.println("8.delete student details from course");
		
			while(exit)
			{
				
				try {
					System.out.println("Enter your choice");
					switch(sc.nextInt())
					{
					case 1:
						//register new Student
						System.out.println("enter Student Details :"
								+ " firstName,  lastName,  email,  password,  course,  date");
						
						Student s1=new Student(sc.next(), sc.next(), sc.next(), sc.next(), Course.valueOf(sc.next().toUpperCase()), LocalDate.parse(sc.next()));
						dao.insertStudentDetails(s1);
						System.out.println("Registered student :\n"+s1);
						break;
					case 2:
						System.out.println("enter email and password");
						String result=dao.login(sc.next(), sc.next());
						System.out.println(result);
						break;
					case 3:
						System.out.println("enter the Student id :");
						int id=sc.nextInt();
						System.out.println("update Course :");
						String course=sc.next().toUpperCase();
						System.out.println("course list :");
						String s=dao.updateCourse(id,Course.valueOf(course));
						System.out.println(s);
						break;
					case 4:
						System.out.println("enter the course name from the list ");
						System.out.println(dao.getCourseDetails(Course.valueOf(sc.next().toUpperCase())));
						break;
					case 5:
						//show all students
						List<Student>list=dao.getAllStudents();
						list.forEach(v-> System.out.println(v));
						break;
					case 6:
						//bulk updation
						System.out.println("enter the course");
						String c=sc.next().toUpperCase();
						System.out.println("Enter new Date");
						String date=sc.next();
						String res=dao.updateAllDate(Course.valueOf(c),LocalDate.parse(date));
						System.out.println(res);
						break;
					case 7:
						//delete specific student details
						System.out.println("enter the Student id");
						System.out.println(dao.deleteDetails(sc.nextInt()));
						break;
					case 8:
						//delete by course
						System.out.println("enter the course");
						System.out.println(dao.deletefromCourse(Course.valueOf(sc.next().toUpperCase())));
						break;
						default :
							exit=false;
							break;
					}
					
				}catch (Exception e) {
					sc.nextLine();
					e.printStackTrace();
				}
				
			}
			
		}  //sf.close() --> clean up DB connection pool!
	

	}

}
