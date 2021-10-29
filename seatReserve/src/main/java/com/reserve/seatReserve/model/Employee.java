package com.reserve.seatReserve.model;


import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Employee")
public class Employee {
	
	@Transient
	public 	static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	
	@NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String firstName;
    private String lastName;

    @NotBlank
    private String project;
    
    @NotBlank
    private String password;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String emailId;
    
    
    @NotBlank
    @Size(max = 100)
    private String officeLocation;
    
    
    private long phoneNumber;
    
    @DBRef
    private Set<Role> roles;
    
    public Employee() {}

	

	public Employee(String firstName, String lastName,String project,
			String password, String emailId,
			String officeLocation, long phoneNumber, Set<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.project = project;
		this.password = password;
		this.emailId = emailId;
		this.officeLocation = officeLocation;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", project=" + project
				+ ", emailId=" + emailId + ", officeLocation=" + officeLocation + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
    
    
	
}
