package com.demo.MOW.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Meal {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String mealName;
	private String description;
	private String preparation;
	@ManyToOne
    @JoinColumn(name = "posted_user_id") // Assuming you have a column named user_id in the Meal table
    private User postedUser;
	
	public long getId() {
		return id;
	}
	public User getPostedUser() {
		return postedUser;
	}
	public void setPostedUser(User postedUser) {
		this.postedUser = postedUser;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPreparation() {
		return preparation;
	}
	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}
	
}
