package com.zensar.room.bean;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Room_table")
public class Room {
	@Id
	int id;
	
	String code;
	
	int capacity;

	public Room(int id, String code, int capacity) {
		super();
		this.id = id;
		this.code = code;
		this.capacity = capacity;
	}

	public Room(int id) {
		super();
		this.id = id;
	}

	public Room() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(capacity, code, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return capacity == other.capacity && Objects.equals(code, other.code) && id == other.id;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", code=" + code + ", capacity=" + capacity + "]";
	}
	
}
