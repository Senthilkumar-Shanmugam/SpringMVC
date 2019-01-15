package lab.springsecurity.dao;

import lab.springsecurity.model.User;

public interface UserDetailsDao {
	  User findUserByUsername(String username);
}
