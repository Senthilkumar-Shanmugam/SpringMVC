package lab.spring.mvc5.hello.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.spring.mvc5.hello.model.User;

@Repository
public class UserDaoImpl implements UserDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(User user) {
          sessionFactory.getCurrentSession().save(user);
	}

	 @Override
	   public List<User> list() {
	      @SuppressWarnings("unchecked")
	      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
	      return query.getResultList();
	   }

}
