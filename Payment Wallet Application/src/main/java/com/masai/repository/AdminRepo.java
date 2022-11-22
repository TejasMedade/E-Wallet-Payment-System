/**
 * 
 */
package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Admin;
import com.masai.model.Customer;

/**
 * @author tejas
 *
 */

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

	public Optional<Admin> findByMobileNumber(String mobileNumber);
	
}
