package lab.springsecurity.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.springsecurity.model.User;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {
	@Autowired
	  private SessionFactory sessionFactory;

	  @Override
	  public User findUserByUsername(String username) {
	    return sessionFactory.getCurrentSession().get(User.class, username);
	  }
	}
