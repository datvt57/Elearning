package com.myclass.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity(name = "categories")
public class Category {
	@Id
	private String id;
	@Column(nullable = false)
	@NotBlank(message = "Please enter Title!")
	private String title;
	@Column
	private String icon;
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Course> course;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Category(String id, String title, String icon) {
		super();
		this.id = id;
		this.title = title;
		this.icon = icon;
	}

	public Category() {

	}
}
