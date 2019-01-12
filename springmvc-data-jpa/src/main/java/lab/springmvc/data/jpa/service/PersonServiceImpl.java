package lab.springmvc.data.jpa.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.springmvc.data.jpa.entity.Person;
import lab.springmvc.data.jpa.dao.PersonDAO;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDAO personDAO;

	@Transactional
	public void add(Person person) {
        personDAO.add(person);
	}

	@Transactional(readOnly = true)
	public List<Person> listPersons() {
		return personDAO.listPersons();
	}

}
