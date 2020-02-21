package com.myclass.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity(name = "roles")
public class Role {
	@Id
	private String id;
	@Column(nullable = false)
	@NotBlank(message = "Please enter Role Name!")
	private String name;
	@Column(nullable = false)
	@NotBlank(message = "Please enter Description!")
	private String description;
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<User> user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Role(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Role() {

	}
}
