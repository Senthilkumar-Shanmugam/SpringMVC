package lab.springmvc.data.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import lab.springmvc.data.jpa.entity.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {
	
	@PersistenceContext
	private EntityManager em;

	public void add(Person person) {
      em.persist(person);
	}

	public List<Person> listPersons() {
		CriteriaQuery<Person> cQry = em.getCriteriaBuilder().createQuery(Person.class);
	    @SuppressWarnings("unused")
		Root<Person> root = cQry.from(Person.class);
	    return em.createQuery(cQry).getResultList();
	}

}
