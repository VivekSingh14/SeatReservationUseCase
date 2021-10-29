package com.reserve.seatReserve.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ReserveSeat")
public class Reserve {

	@Id
	private long id;
	
	@NotBlank
	private Date startDate;
	
	@NotBlank
	private Date endDate;
	
	private Employee employee;
	
	private Desk desk;

	public Reserve(Date startDate, Date endDate, Employee employee, Desk desk) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.employee = employee;
		this.desk = desk;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Desk getDesk() {
		return desk;
	}

	public void setDesk(Desk desk) {
		this.desk = desk;
	}
		
	
}
