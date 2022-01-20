package com.huzaifabinzahoor.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huzaifabinzahoor.hibernate.demo.entity.Course;
import com.huzaifabinzahoor.hibernate.demo.entity.Instructor;
import com.huzaifabinzahoor.hibernate.demo.entity.InstructorDetails;
import com.huzaifabinzahoor.hibernate.demo.entity.Review;
import com.huzaifabinzahoor.hibernate.demo.entity.Student;

public class CreateCourseandStudentDemo {

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

			// create a course
			Course theCourse = new Course("The Era of Learning ");
			session.save(theCourse);
			
			// create the students
			Student student1 = new Student("John", "Doe", "John@gmail.com");
			Student student2 = new Student("Marry", "Public", "public@gmail.com");
			
			theCourse.addStudent(student1);
			theCourse.addStudent(student2);
			
			session.save(student1);
			session.save(student2);
			
			System.out.println("saved students:" +theCourse.getStudents());
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done !!! ");

		} finally {
			session.close();
			factory.close();
		}
	}

}
