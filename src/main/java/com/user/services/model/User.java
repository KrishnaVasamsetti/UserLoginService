package com.user.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column
	public int id;

	@Column
	public String name;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}