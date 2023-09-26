package com.poc.resortmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.resortmanagementsystem.entity.Resort;

import java.util.List;

@Repository
public interface ResortRepository extends JpaRepository<Resort, Long> {
    boolean existsByName(String name);

    List<Resort> findByStateAndCity(String selectedState, String selectedCity);

    List<Resort> findByState(String selectedState);

    List<Resort> findByCity(String selectedCity);
    //List<Resort> findByStateAndCity(String state, String city);
}
