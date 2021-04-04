package backend.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User {

	@Column(name="name", unique=false, nullable=false)
	protected String name;
	
	@Column(name="surname", unique=false, nullable=false)
	protected String surname;
	
	@Column(name="email", unique=true, nullable=false)
	protected String email;
	
	@Column(name="password", unique=false, nullable=false)
	protected String password;
	
	@Column(name="address", unique=false, nullable=true)
	protected Long addressId;
	
	@Column(name="phone_num", unique=false, nullable=true)
	protected String phoneNum;
	
	public User() {
		
	}
	
	public User(String name, String surname, String email, String password, Long addressId, String phoneNum) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.addressId = addressId;
		this.phoneNum = phoneNum;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getAddress() {
		return addressId;
	}
	public void setAddress(Long addressId) {
		this.addressId = addressId;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
