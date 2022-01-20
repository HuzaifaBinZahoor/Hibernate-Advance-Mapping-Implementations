package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huzaifabinzahoor.hibernate.demo.entity.Course;
import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;
import com.huzaifabinzahoor.hibernate.demo.entity.Review;

public class CreateCourseandReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			System.out.println("transaction is begining");
			session.beginTransaction();

			// create a course
			Course theCourse = new Course("The Era of Learning ");
			
			// add some reviews
			Review theReview = new Review("This course is interesting");
			Review theReview1 = new Review("Excellent ! ");
			Review theReview2 = new Review("So precise !!");
			
			theCourse.addReview(theReview);
			theCourse.addReview(theReview1);
			theCourse.addReview(theReview2);
			
			System.out.println("Saving the course");
			System.out.println(theCourse);
			System.out.println("Saving the reviews as well");
			System.out.println(theCourse.getReviews());
			// save the course and also save the reviews
			session.save(theCourse);
			
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done !!! ");

		} finally {
			session.close();
			factory.close();
		}
	}

}
