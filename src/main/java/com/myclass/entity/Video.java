package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity(name = "videos")
public class Video {
	@Id
	private String id;
	@Column(nullable = false)
	@NotBlank(message = "Please enter Title!")
	private String tile;
	@Column
	private String url;
	@Column
	private int timeCount;
	@Column(name = "course_id", nullable = false)
	@NotBlank(message = "Please enter Course ID!")
	private String courseid;
	@Column
	private String image;

	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_videos_to_courses"))
	private Course course;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTile() {
		return tile;
	}

	public void setTile(String tile) {
		this.tile = tile;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Video(String id, String tile, String url, int timeCount, String courseid, String image) {
		super();
		this.id = id;
		this.tile = tile;
		this.url = url;
		this.timeCount = timeCount;
		this.courseid = courseid;
		this.image = image;
	}

	public Video() {

	}
}
