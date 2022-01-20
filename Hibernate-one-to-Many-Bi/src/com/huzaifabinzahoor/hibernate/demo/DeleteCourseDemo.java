package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huzaifabinzahoor.hibernate.demo.entity.Course;
import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;

public class DeleteCourseDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			System.out.println("transaction is begining");
			session.beginTransaction();

			// get a course
			int theId = 10;
			Course theCourse = session.get(Course.class, theId);

			// delete course
			System.out.println("Deleting the Course: " + theCourse);
			session.delete(theCourse);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done !!! ");

		} finally {
			session.close();
			factory.close();
		}
	}

}
