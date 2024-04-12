package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.microservice.connector.RestCallUtil;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

/**
 * 
 * @author knad0001
 */

public class UserManagerImpl implements UserManager {

	private RestCallUtil restUtil;

	public UserManagerImpl() {
		restUtil = new RestCallUtil("http://user:8083/user");
	}

	public void registerUser(String username, String name, String lastname, String password, Role role) {
		String requestUrl = "/register";
		User user = new User(username, name, lastname, password, role);
		restUtil.getObjectFromPostEndpoint(requestUrl, user, User.class, null);
	}

	public User getUserByUsername(String username) {
		if (username == null || username.equals("")) {
			return null;
		}
		String requestUrl = "/byName/" + username;
		return restUtil.getObjectFromGetEndpoint(requestUrl, User.class);
	}

	public boolean deleteUserById(int id) {
		String requestUrl = "/deleteById/" + id;
		return restUtil.getObjectFromGetEndpoint(requestUrl, Boolean.class);
	}

	public Role getRoleByLevel(int level) {
		String requestUrl = "/role/" + level;
		return restUtil.getObjectFromGetEndpoint(requestUrl, Role.class);
	}

	public boolean doesUserAlreadyExist(String username) {
		String requestUrl = "/exists/" + username;
		return restUtil.getObjectFromGetEndpoint(requestUrl, Boolean.class);
	}

	public boolean validate(User user) {
		if (user.getFirstname().isEmpty() || user.getPassword().isEmpty() || user.getRole() == null
				|| user.getLastname() == null || user.getUsername() == null) {
			return false;
		}

		return true;
	}

}
