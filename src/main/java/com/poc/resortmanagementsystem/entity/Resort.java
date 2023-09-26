package com.poc.resortmanagementsystem.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "resorts")
@Getter
@Setter
public class Resort {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;
	@Column
	private String address;

	@Column
	private String state;

	@Column
	private String city;

	@OneToMany(mappedBy = "resort", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Room> rooms = new HashSet<>();



	public Resort() {
		
	}

	public Resort(Long id, String name, String address, String state, String city, Set<Room> rooms) {
		
		this.id = id;
		this.name = name;
		this.address = address;
		this.state=state;
		this.city=city;
		this.rooms = rooms;
	}

	public Resort(String name, String address,String state, String city,Set<Room> rooms) {
		
		this.name = name;
		this.address = address;
		this.state=state;
		this.city=city;
		this.rooms = rooms;
	}

	public Resort(Long id, String name, String state, String city,String address) {
		
		this.id = id;
		this.name = name;
		this.address = address;
		this.state=state;
		this.city=city;

	}
	
	
}
