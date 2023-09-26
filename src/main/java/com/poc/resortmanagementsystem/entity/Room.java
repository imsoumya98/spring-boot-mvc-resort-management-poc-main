package com.poc.resortmanagementsystem.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resort_id")
	private Resort resort;

	public Room() {

	}

	public Room(Long id, String description, Resort resort) {
		super();
		this.id = id;
		this.description = description;
		this.resort = resort;
	}

	public Room(Long id, String description) {

		this.id = id;
		this.description = description;
	}

	public Room(String description, Resort resort) {
		super();
		this.description = description;
		this.resort = resort;
	}

	public Room(String description) {
		super();
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Resort getResort() {
		return resort;
	}

	public void setResort(Resort resort) {
		this.resort = resort;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Room room = (Room) o;
		return Objects.equals(id, room.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
