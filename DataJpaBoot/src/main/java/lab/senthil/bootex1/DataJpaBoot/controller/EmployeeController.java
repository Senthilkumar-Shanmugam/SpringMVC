package lab.senthil.bootex1.DataJpaBoot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lab.senthil.bootex1.DataJpaBoot.exception.ResourceNotFoundException;
import lab.senthil.bootex1.DataJpaBoot.model.Employee;
import lab.senthil.bootex1.DataJpaBoot.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	//@GetMapping("/employees/{id}")
	@RequestMapping(value = "/employee/{id}",method = RequestMethod.GET, produces="application/json")
	public 	@ResponseBody Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) 
			throws ResourceNotFoundException{
		Employee employee =employeeRepository.findById(employeeId)
		          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		//return ResponseEntity.ok().body(employee);
		return employee;

	}
	
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
		
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id,
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found for this id:"+id));
		employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public Map<String,Boolean> deleteEmployee(@PathVariable(value = "id") Long id) 
	                throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found for this id:"+id));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

	}

}
