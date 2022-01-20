package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetails.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create the objects
			Instructor Instructor2 = new Instructor("Alex", "Max", "alex123@gmail.com");
			InstructorDetails instructorDetails2 = new InstructorDetails("Alex in Action", "Study");
			
			Instructor Instructor3 = new Instructor("Li", "Man", "liman@gmail.com");
			InstructorDetails instructorDetails3 = new InstructorDetails("Stigma", "Shnell");
			
			Instructor Instructor4 = new Instructor("Maxxel", "Luois", "maxweelluois@gmail.com");
			InstructorDetails instructorDetails4 = new InstructorDetails("Volvo Trucks", "Exploring");
			
			Instructor Instructor5 = new Instructor("Ahmed", "Humble", "humbleahmed@gmail.com");
			InstructorDetails instructorDetails5 = new InstructorDetails("Brille Styles", "Surfing");
			
			// associate the objects
			Instructor2.setInstructorDetails(instructorDetails2);
			Instructor3.setInstructorDetails(instructorDetails3);
			Instructor4.setInstructorDetails(instructorDetails4);
			Instructor5.setInstructorDetails(instructorDetails5);
			
			// begin the transaction
			System.out.println("transaction is begining");
			session.beginTransaction();
			// save the instructor
			// Point to Note here: this will also save the instructor detail object (instructorDetails1)
			// As we have CascadeType.ALL (means it will save the actual object and any associated object)
			System.out.println("Saving the Object: "+Instructor2);
			System.out.println("Saving the Object: "+Instructor3);
			System.out.println("Saving the Object: "+Instructor4);
			System.out.println("Saving the Object: "+Instructor5);
			session.save(Instructor2);
			session.save(Instructor3);
			session.save(Instructor4);
			session.save(Instructor5);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done !!! ");

		} finally {
			factory.close();
		}
	}

}
