package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;

public class GetInstructorDetail {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// begin the transaction
			System.out.println("transaction is begining");
			session.beginTransaction();

			// get instructor by primary key
			int theId = 5;
			InstructorDetails theInstructorDetails = session.get(InstructorDetails.class, theId);

			// print the instructor Details
			System.out.println("Found the Instructor Detail: " + theInstructorDetails);

			// print the associated instructor
			System.out.println("Found the Instructor: " + theInstructorDetails.getInstructor());

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done !!! ");

		} finally {
			factory.close();
		}
	}

}
