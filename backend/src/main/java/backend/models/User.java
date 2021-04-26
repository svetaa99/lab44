package backend.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@MappedSuperclass
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1145944638748587516L;

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

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", email=" + email + ", password=" + password
				+ ", addressId=" + addressId + ", phoneNum=" + phoneNum + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
