package br.com.antunes.gustavo.carrentproject.model;

import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class Employee extends Person{

	private float salary;
	
	private Date hireDate;
	
	private String jobTitle;
	
	private String department;
	
	public Employee() {}

	public Employee(float salary, Date hireDate, String jobTitle, String department) {
		super();
		this.salary = salary;
		this.hireDate = hireDate;
		this.jobTitle = jobTitle;
		this.department = department;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
