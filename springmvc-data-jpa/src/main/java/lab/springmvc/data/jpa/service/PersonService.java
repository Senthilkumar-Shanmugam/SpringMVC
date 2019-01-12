package lab.springmvc.data.jpa.service;

import java.util.List;

import lab.springmvc.data.jpa.entity.Person;

public interface PersonService {
	void add(Person person);
	List<Person> listPersons();

}
