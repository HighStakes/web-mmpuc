package com;
 
 
public class Student {
	public int id;
	public  String studentNumber;
	public  char className;
	public  char section;
	public  String firstName;
	public  String lastName;
	public  String gender;
	public  String subjects;
	public  String contact;
	 
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	 
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 
	public char getClassName() {
		return className;
	}
	public void setClassName(char className) {
		this.className = className;
	}
	public char getSection() {
		return section;
	}
	public void setSection(char section) {
		this.section = section;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	 
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
