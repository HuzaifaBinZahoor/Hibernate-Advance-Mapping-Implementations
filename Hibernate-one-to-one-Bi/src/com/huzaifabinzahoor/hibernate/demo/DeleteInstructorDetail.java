package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;

public class DeleteInstructorDetail {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start the transaction
			session.beginTransaction();

			// get instructor by primary key
			int theId = 3;
			InstructorDetails theInstructorDetails = session.get(InstructorDetails.class, theId);

			// print the instructor Details
			System.out.println("Found the Instructor Detail: " + theInstructorDetails);

			// print the associated instructor
			System.out.println("Found the Instructor: " + theInstructorDetails.getInstructor());

			/*
			 * this code block is specifically to break the bi-directional mapping. use only
			 * if I want to remove only Instructor details // remove the associated object
			 * reference // break bi-directional link
			 * theInstructorDetails.getInstructor().setInstructorDetails(null);
			 */
			// delete that instructor
			if (theInstructorDetails != null) {
				System.out.println("Deleting the Instructor: " + theInstructorDetails);

				// Note: this will also delete the Instructor Detail object as we have
				// CascadeType.ALL
				session.delete(theInstructorDetails);
			}

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done !!! ");

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
