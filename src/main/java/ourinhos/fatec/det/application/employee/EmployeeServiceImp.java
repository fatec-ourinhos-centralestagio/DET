package ourinhos.fatec.det.application.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ourinhos.fatec.det.domain.employee.EmployeeRepository;
import ourinhos.fatec.det.domain.employee.EmployeeService;
import ourinhos.fatec.det.domain.employee.vo.EmployeeVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.employee.EmployeeEntity;

import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<HttpStatus> createEmployee(EmployeeVO employee) {
        return employeeRepository.createEmployee(employee);
    }

    public ResponseEntity<Optional<EmployeeEntity>> getEmployee(String employeeId) {
        return employeeRepository.getEmployee(employeeId);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteEmployee(String employeeId) {
       return employeeRepository.deleteEmployee(employeeId);
    }

    @Override
    public ResponseEntity<HttpStatus> updateEmployee(EmployeeVO employee, String employeeId) {
        return employeeRepository.updateEmployee(employee, employeeId);
    }
}
