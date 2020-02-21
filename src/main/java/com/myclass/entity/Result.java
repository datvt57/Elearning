package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity(name = "results")
public class Result {
	@Id
	private String id;
	@Column(nullable = false)
	@NotBlank(message = "Please enter Title!")
	private String title;
	@Column(name = "course_id", nullable = false)
	@NotBlank(message = "Please enter Course ID!")
	private String courseid;

	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_results_to_courses"))
	private Course course;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public Result(String id, String title, String courseid) {
		super();
		this.id = id;
		this.title = title;
		this.courseid = courseid;
	}

	public Result() {

	}
}
