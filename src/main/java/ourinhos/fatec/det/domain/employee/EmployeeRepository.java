package ourinhos.fatec.det.domain.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ourinhos.fatec.det.domain.employee.vo.EmployeeVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.employee.EmployeeEntity;

import java.util.Optional;

public interface EmployeeRepository {

    ResponseEntity<HttpStatus> createEmployee(EmployeeVO user);

    ResponseEntity<Optional<EmployeeEntity>> getEmployee(String userId);

    ResponseEntity<HttpStatus> deleteEmployee (String userId);

    ResponseEntity<HttpStatus> updateEmployee(EmployeeVO user, String userId);
}
