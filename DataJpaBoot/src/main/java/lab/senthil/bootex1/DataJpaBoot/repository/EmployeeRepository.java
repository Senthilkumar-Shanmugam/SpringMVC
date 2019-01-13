package lab.senthil.bootex1.DataJpaBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.senthil.bootex1.DataJpaBoot.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
