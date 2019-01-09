package lab.spring.mvc5.hello.service;

import java.util.List;

import lab.spring.mvc5.hello.model.User;

public interface UserService {
	void save(User user);
	List<User> list();

}
