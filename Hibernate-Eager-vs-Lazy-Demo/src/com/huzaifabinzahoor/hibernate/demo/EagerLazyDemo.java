package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huzaifabinzahoor.hibernate.demo.entity.Course;
import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			System.out.println("transaction is begining");
			session.beginTransaction();

			// we need to get the instructor from the db
			// let suppose that instructor has the id of 1
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);
			
			System.out.println("huzaifabinzahoor: Instructor: "+instructor);
			
			// get courses for the instructor
			System.out.println("huzaifabinzahoor: Courses for the Instructor: "+instructor.getCourses());

			// commit transaction
			session.getTransaction().commit();

			System.out.println("huzaifabinzahoor: Done !!! ");

		} finally {
			session.close();
			factory.close();
		}
	}

}
