package br.com.antunes.gustavo.carrentproject.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;

@Entity
public class Employee extends Person{

	private BigDecimal salary;
	
	private Date hireDate;
	
	private String jobTitle;
	
	private String department;
	
	public Employee() {}

	public Employee(BigDecimal salary, Date hireDate, String jobTitle, String department) {
		super();
		this.salary = salary;
		this.hireDate = hireDate;
		this.jobTitle = jobTitle;
		this.department = department;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(department, hireDate, jobTitle, salary);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(department, other.department) && Objects.equals(hireDate, other.hireDate)
				&& Objects.equals(jobTitle, other.jobTitle) && Objects.equals(salary, other.salary);
	}
}
