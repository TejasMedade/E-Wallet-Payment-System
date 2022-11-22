/**
 * 
 */
package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.CurrentCustomerSession;

/**
 * @author tejas
 *
 */
@Repository
public interface CurrentCustomerSessionRepo extends JpaRepository<CurrentCustomerSession, Integer> {

	public Optional<CurrentCustomerSession> findByKey(String key);

	public Optional<CurrentCustomerSession> findByCustomerMobileNumber(String customerMobileNumber);
}
