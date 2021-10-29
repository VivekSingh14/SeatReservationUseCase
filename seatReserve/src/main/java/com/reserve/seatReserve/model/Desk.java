package com.reserve.seatReserve.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Desk")
public class Desk {

	@Transient
    public static final String SEQUENCE_NAME = "desks_sequence";
	
	@Id
    private long id;
	
	private String office;
	
	
	private long floor;
	
	public Desk() {}

	public Desk(String office, long floor) {
		this.office = office;
		this.floor = floor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public long getFloor() {
		return floor;
	}

	public void setFloor(long floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "Desk [id=" + id + ", office=" + office + ", floor=" + floor + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
