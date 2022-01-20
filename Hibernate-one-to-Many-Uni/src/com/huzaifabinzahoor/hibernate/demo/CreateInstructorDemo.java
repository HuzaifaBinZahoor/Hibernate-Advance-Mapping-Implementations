package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huzaifabinzahoor.hibernate.demo.entity.Course;
import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;
import com.huzaifabinzahoor.hibernate.demo.entity.Review;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Review.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// creating the objects
			Instructor Instructor2 = new Instructor("AAA", "BBB", "AAABBB@gmail.com");
			Instructor Instructor3 = new Instructor("QQQ", "GFGF", "BBBBB@gmail.com");
			Instructor Instructor4 = new Instructor("ZZZZ", "VVV", "ASSSSS@gmail.com");

			InstructorDetails tempInstructorDetails2 = new InstructorDetails("www.youtube.com/AAA", "AAAVideos");
			InstructorDetails tempInstructorDetails3 = new InstructorDetails("www.youtube.com/WA@!", "!@@@os");
			InstructorDetails tempInstructorDetails4 = new InstructorDetails("www.youtube.com/!@@12", "WDE31s");

			// associating the objects
			Instructor2.setInstructorDetails(tempInstructorDetails2);
			Instructor3.setInstructorDetails(tempInstructorDetails3);
			Instructor4.setInstructorDetails(tempInstructorDetails4);

			// begin the transaction
			System.out.println("transaction is begining");
			session.beginTransaction();

			// save the Teacher object
			// NOTE: as soon as the Teacher object will save in the DB
			// The Teacher Detail will also be saved in the DB, because we have created the
			// CASCADE.ALL association link
			// between the objects above. So, we only save the Teacher Object
			System.out.println("Saving the Teacher objects in DB");
			session.save(Instructor2);
			session.save(Instructor3);
			session.save(Instructor4);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done !!! ");

		} finally {
			session.close();
			factory.close();
		}
	}

}
