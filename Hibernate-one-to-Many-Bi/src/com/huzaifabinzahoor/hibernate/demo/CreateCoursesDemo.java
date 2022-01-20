package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huzaifabinzahoor.hibernate.demo.entity.Course;
import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;

public class CreateCoursesDemo {

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
			int theId = 3;
			Instructor instructor = session.get(Instructor.class, theId);

			// Now, we need to create some courses
			Course theCourse1 = new Course("The Cup 1");
			Course theCourse2 = new Course("The Cup 2");

			// add that courses to the above instructor
			instructor.add(theCourse1);
			instructor.add(theCourse2);

			// save the courses
			session.save(theCourse1);
			session.save(theCourse2);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done !!! ");

		} finally {
			session.close();
			factory.close();
		}
	}

}
