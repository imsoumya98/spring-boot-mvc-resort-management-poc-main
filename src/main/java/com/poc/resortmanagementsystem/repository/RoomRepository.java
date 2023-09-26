package com.poc.resortmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.resortmanagementsystem.entity.Resort;
import com.poc.resortmanagementsystem.entity.Room;
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findByResort(Resort resort);
}
