package net.javaguides.employeeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee")

public class Employee {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
private Long id;
private String firstName;
private String lastName;
@Column(nullable = false, unique = true)
private String email;
private String departmentCode;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public void setDepartmentCode(String departmentCode) {
	this.departmentCode = departmentCode;
}
public String getDepartmentCode() {
	return departmentCode;
}

public Employee(Long id, String firstName, String lastName, String email, String departmentCode) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.departmentCode = departmentCode;
}
public Employee() {
	super();
	// TODO Auto-generated constructor stub
}



}
