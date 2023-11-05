package com.demo.MOW.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String role;
	private String roleDescription;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	public Set<User> getUsers() {
        return users;
    }
}
