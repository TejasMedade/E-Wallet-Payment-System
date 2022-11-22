/**
 * 
 */
package com.masai.services;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.LogoutException;
import com.masai.exceptions.UserException;
import com.masai.model.Admin;
import com.masai.model.CurrentAdminSession;
import com.masai.model.User;

/**
 * @author tejas
 *
 */
public interface LoginLogoutAdminService {

	public CurrentAdminSession loginAdmin(User user) throws LoginException, AdminException;

	public String logoutAdmin(String key) throws LogoutException;

	public User authenticateAdmin(User user, String key) throws UserException, AdminException, LoginException;

	public Admin validateAdmin(String key) throws AdminException, LoginException;

}
