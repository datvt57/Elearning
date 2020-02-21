package com.myclass.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "users")
public class User {
	@Id
	private String id;
	@Column(nullable = false)
	@NotBlank(message = "Please enter Email!")
	@Email(message = "Email is incorrect!")
	private String email;
	@Column(nullable = false)
	@NotBlank(message = "Please enter Fullname!")
	private String fullname;
	@Column(nullable = false)
	@NotBlank(message = "Please enter Password!")
	@Length(min= 4,message = "Password's length minimum is 4!")
	private String password;
	@Column(name = "role_id", nullable = false)
	@NotBlank(message = "Please enter Role ID!")
	private String roleid;
	@Column
	private String avatar;
	@Column
	private String phone;
	@Column
	private String address;
	@Column
	private String website;
	@Column
	private String facebook;

	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false ,foreignKey = @ForeignKey(name = "fk_users_to_roles"))
	@JsonIgnore
	private Role role;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserCourse> userCourse;

	public User(String id, String email, String fullname, String password, String roleid) {
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.roleid = roleid;
	}

	public User() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
