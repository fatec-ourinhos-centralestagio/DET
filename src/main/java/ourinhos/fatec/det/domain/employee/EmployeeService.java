package ourinhos.fatec.det.domain.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ourinhos.fatec.det.domain.employee.vo.EmployeeVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.employee.EmployeeEntity;

import java.util.Optional;

public interface EmployeeService {

    ResponseEntity<HttpStatus> createEmployee(EmployeeVO employee);

    ResponseEntity<Optional<EmployeeEntity>> getEmployee(String employeeId);

    ResponseEntity<HttpStatus> deleteEmployee (String employeeId);

    ResponseEntity<HttpStatus> updateEmployee(EmployeeVO employee, String employeeId);
}
