package lab.senthil.bootex1.DataJpaBoot.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lab.senthil.bootex1.DataJpaBoot.exception.ResourceNotFoundException;
import lab.senthil.bootex1.DataJpaBoot.model.Employee;
import lab.senthil.bootex1.DataJpaBoot.repository.EmployeeRepository;

@RestController  // No explicit mapping is required
@RequestMapping("/api/v1/")
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	//@GetMapping("/employees")
	//@ResponseBody
	@RequestMapping(value="/employees",method = RequestMethod.GET, produces = {"application/xml","application/json"})
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	//@GetMapping("/employees/{id}")
    @ApiOperation(value = "Get an employee by Id")
	@RequestMapping(value = "/employees/{id}",method = RequestMethod.GET, produces="application/json",consumes="application/json")
	public 	Resource<Employee> getEmployeeById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true)
	@PathVariable(value = "id") Long employeeId) 
			throws ResourceNotFoundException{
		/*Employee employee =employeeRepository.findById(employeeId)
		          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		*///return ResponseEntity.ok().body(employee);
    	Optional<Employee> employee = employeeRepository.findById(employeeId);
    	if (!employee.isPresent())
    	throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
    	
    	Resource<Employee> empResource = new Resource<Employee>(employee.get());
    	ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllEmployees());
    	empResource.add(linkTo.withRel("all-employees"));
		return empResource;

	}
	
	@PostMapping("/employees")
    @ApiOperation(value = "Add an employee")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Employee> createEmployee(@ApiParam(value = "Employee object store in database table", required = true)  @Valid @RequestBody Employee employee) {
		Employee saved = employeeRepository.save(employee);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		               .buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@ApiOperation(value = "Update an employee")
    @PutMapping("/employees/{id}")
    public ResponseEntity < Employee > updateEmployee(
        @ApiParam(value = "Employee Id to update employee object", required = true) @PathVariable(value = "id") Long employeeId,
        @ApiParam(value = "Update employee object", required = true) @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    @ApiOperation(value = "Delete an employee")
    @DeleteMapping("/employees/{id}")
    public Map < String, Boolean > deleteEmployee(
        @ApiParam(value = "Employee Id from which employee object will delete from database table", required = true) @PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employeeRepository.delete(employee);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }}
