package com.iiht.evaluation.coronokit.model;

public class UserProfile {
	
	private int id;
	private String userName;
	private String email;
	private String contactNumber;
	private String deliveryAddress;
	
	public UserProfile(int id, String userName, String email, String contactNumber, String deliveryAddress) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.deliveryAddress = deliveryAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

}
