package com.masai.services;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CurrentAdminSessionException;
import com.masai.model.Admin;
import com.masai.model.CurrentAdminSession;

public interface CurrentAdminSessionService {

	public CurrentAdminSession getCurrentAdminSession(String key) throws CurrentAdminSessionException;

	public Admin getAdminDetails(String key) throws AdminException, CurrentAdminSessionException;

	public Integer getAdminId(String key) throws CurrentAdminSessionException;

}
