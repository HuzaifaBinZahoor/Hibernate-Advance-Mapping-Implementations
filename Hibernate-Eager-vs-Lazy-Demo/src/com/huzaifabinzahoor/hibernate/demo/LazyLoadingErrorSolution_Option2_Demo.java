package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.huzaifabinzahoor.hibernate.demo.entity.Course;
import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;

public class LazyLoadingErrorSolution_Option2_Demo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			System.out.println("transaction is begining");
			session.beginTransaction();

			// options 2: Hibernate query with HQL

			// we need to get the instructor from the db
			// let suppose that instructor has the id of 1
			int theId = 1;

			Query<Instructor> query = session.createQuery(
					"select i from Instructor i " + "JOIN FETCH i.courses " + "where i.id=:theInstructorId ",
					Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			//execute the query and get the instructor
			Instructor instructor = query.getSingleResult();

			System.out.println("huzaifabinzahoor: Instructor: " + instructor);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("session has been closed here. No open session");
			session.close();
			// get courses for the instructor
			System.out.println("huzaifabinzahoor: Courses for the Instructor: " + instructor.getCourses());

			System.out.println("huzaifabinzahoor: Done !!! ");

		} finally {

			factory.close();
		}
	}

}
