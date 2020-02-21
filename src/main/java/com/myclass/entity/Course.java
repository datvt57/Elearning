package com.myclass.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "courses")
public class Course {
	@Id
	private String id;
	@Column(nullable = false)
	@NotBlank(message = "Please enter Title!")
	private String title;
	@Column
	private String image;
	@Column(name = "letures_count")
	private int letureCount;
	@Column(name = "hour_count")
	private int hourCount;
	@Column(nullable = false)
	@NotBlank(message = "Please enter Description!")
	private String desciption;
	@Column(name = "category_id", nullable = false)
	@NotBlank(message = "Please enter Category ID!")
	private String categoryid;
	@Column(name="view_count")
	private int viewCount;
	@Column
	private float price;
	@Column
	private int discount;
	@Column(name="promotion_price")
	private float promotionPrice;
	@Column(name="last_update")
	@JsonIgnore
	private Timestamp lastUpdate;
	
	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_courses_to_categories"))
	@JsonIgnore
	private Category category;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Video> video;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Result> result;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserCourse> userCourse;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLetureCount() {
		return letureCount;
	}

	public void setLetureCount(int letureCount) {
		this.letureCount = letureCount;
	}

	public int getHourCount() {
		return hourCount;
	}

	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public Course(String id, String title, String image, int letureCount, int hourCount, String desciption,
			String categoryid) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.letureCount = letureCount;
		this.hourCount = hourCount;
		this.desciption = desciption;
		this.categoryid = categoryid;
	}

	public Course() {

	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public float getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Video> getVideo() {
		return video;
	}

	public void setVideo(List<Video> video) {
		this.video = video;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public List<UserCourse> getUserCourse() {
		return userCourse;
	}

	public void setUserCourse(List<UserCourse> userCourse) {
		this.userCourse = userCourse;
	}
}
