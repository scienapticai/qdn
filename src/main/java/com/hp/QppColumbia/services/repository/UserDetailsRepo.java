package com.hp.QppColumbia.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hp.QppColumbia.services.entity.UserDetails;

public interface UserDetailsRepo extends JpaRepository<com.hp.QppColumbia.services.dao.UserDetails, Integer> {
	@Query(value = "Select (`role`) from user_details where email = ?1", nativeQuery = true)
	public String findRoleByEmail(String email);
}
