package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "user_courses")
public class UserCourse {
	@Id
	private String id;
	@Column(name = "user_id", nullable = false)
	@NotBlank(message = "Please enter User ID!")
	private String userid;
	@Column(name = "course_id", nullable = false)
	@NotBlank(message = "Please enter Course ID!")
	private String courseid;
	@Column(name = "role_id", nullable = false)
	private String roleid;

	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_user_courses_to_courses"))
	@JsonIgnore
	private Course course;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_user_courses_to_users"))
	@JsonIgnore
	private User user;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public UserCourse(String userid, String courseid, String roleid) {
		super();
		this.userid = userid;
		this.courseid = courseid;
		this.roleid = roleid;
	}

	public UserCourse() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
