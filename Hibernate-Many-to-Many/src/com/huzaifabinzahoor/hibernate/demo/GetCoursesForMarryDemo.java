package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huzaifabinzahoor.hibernate.demo.entity.Course;
import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;
import com.huzaifabinzahoor.hibernate.demo.entity.Review;
import com.huzaifabinzahoor.hibernate.demo.entity.Student;

public class GetCoursesForMarryDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			System.out.println("transaction is begining");
			session.beginTransaction();
			
			// get the Marry from the Database
			int theId = 2;
			Student theStudent = session.get(Student.class, theId);
			
			// printing the data of Marry
			System.out.println("Marry Data is: " +theStudent);
			
			// also printing the courses related to this student
			System.out.println("The Registered courses are: "+theStudent.getCourses());
			
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done !!! ");

		} finally {
			session.close();
			factory.close();
		}
	}

}
