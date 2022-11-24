/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CurrentAdminSessionException;
import com.masai.model.Admin;
import com.masai.model.CurrentAdminSession;
import com.masai.repository.AdminRepo;
import com.masai.repository.CurrentAdminSessionRepo;
import com.masai.services.CurrentAdminSessionService;

/**
 * @author tejas
 *
 */

@Service
public class CurrentAdminSessionServiceImplementation implements CurrentAdminSessionService {

	@Autowired
	private CurrentAdminSessionRepo currentAdminSessionRepo;

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public CurrentAdminSession getCurrentAdminSession(String key) throws CurrentAdminSessionException {

		Optional<CurrentAdminSession> optional_CurrentAdminSession = currentAdminSessionRepo.findByKey(key);

		if (optional_CurrentAdminSession.isPresent()) {

			CurrentAdminSession currentAdminSession = optional_CurrentAdminSession.get();

			return currentAdminSession;
		} else {
			throw new CurrentAdminSessionException("Invalid key, Please Login In !");
		}
	}

	@Override
	public Admin getAdminDetails(String key) throws AdminException, CurrentAdminSessionException {

		Optional<CurrentAdminSession> optional_CurrentAdminSession = currentAdminSessionRepo.findByKey(key);

		if (optional_CurrentAdminSession.isPresent()) {

			CurrentAdminSession currentAdminSession = optional_CurrentAdminSession.get();

			Integer current_CustomerId = currentAdminSession.getAdminId();

			Optional<Admin> optional_admin = adminRepo.findById(current_CustomerId);

			if (optional_admin.isPresent()) {

				Admin admin = optional_admin.get();

				return admin;

			} else {
				throw new AdminException("No Registered Admin Found !");
			}

		} else {
			throw new CurrentAdminSessionException("Invalid key, Please Login In !");
		}

	}

	@Override
	public Integer getAdminId(String key) throws CurrentAdminSessionException {

		Optional<CurrentAdminSession> optional_CurrentAdminSession = currentAdminSessionRepo.findByKey(key);

		if (optional_CurrentAdminSession.isPresent()) {

			CurrentAdminSession currentAdminSession = optional_CurrentAdminSession.get();

			Integer current_adminId = currentAdminSession.getAdminId();

			return current_adminId;

		} else {
			throw new CurrentAdminSessionException("Invalid key, Please Login In !");
		}

	}

}
