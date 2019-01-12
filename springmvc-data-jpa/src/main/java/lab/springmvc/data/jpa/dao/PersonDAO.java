package lab.springmvc.data.jpa.dao;

import java.util.List;

import lab.springmvc.data.jpa.entity.Person;

public interface PersonDAO {
	void add(Person person);
	List<Person> listPersons();

}
