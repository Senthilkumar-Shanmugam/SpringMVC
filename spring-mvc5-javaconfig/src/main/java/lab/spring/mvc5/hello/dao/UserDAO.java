package lab.spring.mvc5.hello.dao;

import java.util.List;

import lab.spring.mvc5.hello.model.User;

public interface UserDAO {
	void save(User user);
	List<User> list();

}
