package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static utils.HibernateUtils.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import pojos.Course;
import pojos.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public String insertStudentDetails(Student s) {
		String mesg="Adding details failed";
		Session session=getFactory().openSession();
		//open a transaction and start it 
		Transaction tr=session.beginTransaction();
		try {
			session.save(s);
			tr.commit();
			mesg="admission succesful";
			
		}//make it runtime exception
		catch (RuntimeException e) {
			if(tr!=null)
				tr.rollback();
			throw e;
		}
		finally {
			if(session!=null)
				session.close();
		}
		return mesg;
	}

	@Override
	public String login(String email, String passw) {
		Session session=getFactory().getCurrentSession();
		System.out.println(email+ " "+passw);
		String mesg="login fail";
		Student std=null;
		String jpql="select s from Student s where s.email=:em and s.password=:pass";
		Transaction tr=session.beginTransaction();
		try {
			 std=session.createQuery(jpql, Student.class).setParameter("em",email).setParameter("pass",passw).getSingleResult();
			 System.out.println(std.getEmail()+ " "+std.getPassword());
			if(std.getEmail().equals(email))
			{
				if(std.getPassword().equals(passw))
				{
					System.out.println("login succesful");
					System.out.println(std);
				}
				else
					System.out.println("invalid password");
			}
			else 
				System.out.println("invalid email");
			tr.commit();
			mesg="login success";
			
		} catch (RuntimeException e) {
		if(tr!=null)
			tr.rollback();
		
		throw e;
		
		}
		return mesg;
	}

	@Override
	public List<Student> getCourseDetails(Course course) {
		List<Student>list=null;
		String jpql="select s from Student s where course=:cs";
		Session session =getFactory().getCurrentSession();
		Transaction tr=session.beginTransaction();
		try {
			list=session.createQuery(jpql, Student.class).setParameter("cs",course).getResultList();
			tr.commit();
			
		}
		catch (RuntimeException e) {
			if(tr!=null)
				tr.rollback();
			e.getMessage();
			throw e;
		}
		return list;
	}

	@Override
	public String updateCourse(int empid,Course course) {
		String mesg=null;
		Student s1=null;
		Session session=getFactory().getCurrentSession();
		Transaction tr=session.beginTransaction();
		try {
			s1=session.get(Student.class, empid);
			System.out.println(s1.getCourse());
			s1.setCourse(course);
			System.out.println(s1.getCourse());
			System.out.println(s1);
			if(s1.getCourse().equals(course))
			{
				mesg="course updation successful";
			}
			else
			{
				mesg="course  updation unsuccessful";
			}
			tr.commit();
			
		}
		catch (RuntimeException e) {
			if(tr!=null)
				tr.rollback();
			e.getMessage();
			throw e;
		}
		return mesg;
	}

	@Override
	public List<Student> getAllStudents() {
		Session session=getFactory().getCurrentSession();
		List<Student>list=null;
		String jpql="select s from Student s";
		Transaction tr=session.beginTransaction();
		try {
			list=session.createQuery(jpql, Student.class).getResultList();
			tr.commit();
			
		}
		catch (RuntimeException e) {
			// TODO: handle exception
			if(tr!=null)
				tr.rollback();
			throw e;
		}
		return list;
	}

	@Override
	public String updateAllDate(Course c1, LocalDate d1) {
		//using single update jpql query
		
		Session session=getFactory().getCurrentSession();
		String mesg=null;
		String jpql="update Student s set date=:dt where course=:c";
		Transaction tr=session.beginTransaction();
		try {
			int result=session.createQuery(jpql).setParameter("dt",d1).setParameter("c",c1).executeUpdate();
			if(result >0)
			{
				mesg="date updation Successful";
			}
			else
			{
				mesg="Date Updation failure";
			}
			tr.commit();
			
		} catch (RuntimeException e) {
		if(tr!=null)
			tr.rollback();
		e.printStackTrace();
		throw e;
		}
		return mesg;
	}

	@Override
	public String deleteDetails(int empid) {
		Session session=getFactory().getCurrentSession();
		String msg=null;
		Transaction tr=session.beginTransaction();
		try {
			Student s=session.get(Student.class, empid);
			session.delete(s);
			msg="delete successfully";
			tr.commit();
		} catch (RuntimeException e) {
		if(tr!=null)
			tr.rollback();
		e.printStackTrace();
		throw e;
		}
		return msg;
	}

	@Override
	public String deletefromCourse(Course c) {
		Session session=getFactory().getCurrentSession();
		String msg=null;
		String jpql="delete from Student s where course=:c1";
		Transaction tr=session.beginTransaction();
		try {
			int result=session.createQuery(jpql).setParameter("c1",c).executeUpdate();
			if(result >0)
			{
				msg="deleted  Successful";
			}
			else
			{
				msg="not deleted";
			}
			tr.commit();
			
		} catch (RuntimeException e) {
		if(tr!=null)
			tr.rollback();
		e.printStackTrace();
		throw e;
		}
		return msg;
	}

}
