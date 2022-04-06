package com.zensar.employee.bean;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Embeddable
@Table(name="role_table")
public class Role {
	@Id
	int id;
	String authority;

	@Override
	public String toString() {
		return "Role []";
	}
	public Role(int id) {
		super();
		this.id = id;
	}
	public Role(int id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
