//package com;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import com.Student;
//import com.HibernateUtil;
//
//public class StudentDAO {
//
//	public void saveStudentDetails(String sNo, char className, char sectionName, String firstName, String lastName,
//			String contactNo, String gender, String subjects) {
////		System.out.println("\n\n NOW \n");
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		try {
//
//			Student student = new Student();
//			student.setStudentNumber(sNo);
//			student.setClassName(className);
//			student.setSection(sectionName);
//			student.setFirstName(firstName);
//			student.setLastName(lastName);
//			student.setGender(gender);
//			student.setContact(contactNo);
//			student.setSubjects(subjects);
//			session.saveOrUpdate(student);
//			//session.persist(student);
//			session.flush();
//			transaction.commit();
//			
////			System.out.println("Student ID :" + sNo);
////			System.out.println("\n\n Details Added \n");
//
//		} catch (HibernateException e) {
//			e.printStackTrace();
//
//		} catch (Exception e) {
//			System.out.println("Error :" + e.getMessage());
//		} finally {
////			if (!transaction.wasCommitted()) {
////				System.out.println("Not committed");
////				transaction.rollback();
////			}
////
////			
//			session.close();
//		}
//
//	}
//
//}