package lab.springmvc.data.jpa;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lab.springmvc.data.jpa.config.AppConfig;
import lab.springmvc.data.jpa.entity.Person;
import lab.springmvc.data.jpa.service.PersonService;

public class MainApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
		PersonService personService = appContext.getBean(PersonService.class);
		
		// Add Persons
	      personService.add(new Person("Rahul", "Gupta", "rahulgupta@company.com"));
	      personService.add(new Person("Akshay", "Sharma", "akshaysharma@company.com"));
	      personService.add(new Person("Ankit", "Sarraf", "ankitsarraf@company.com"));
	      // Get Persons
	      List<Person> persons = personService.listPersons();
	      for (Person person : persons) {
	         System.out.println("Id = "+person.getId());
	         System.out.println("First Name = "+person.getFirst_name());
	         System.out.println("Last Name = "+person.getLast_name());
	         System.out.println("Email = "+person.getEmail());
	         System.out.println();
	      }
		

	}

}
