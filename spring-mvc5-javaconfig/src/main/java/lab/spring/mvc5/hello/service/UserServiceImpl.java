package lab.spring.mvc5.hello.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.spring.mvc5.hello.dao.UserDAO;
import lab.spring.mvc5.hello.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDao;

	@Transactional
	public void save(User user) {
      userDao.save(user);
	}

	@Transactional(readOnly=true)
	public List<User> list() {
		return userDao.list();
	}

}
