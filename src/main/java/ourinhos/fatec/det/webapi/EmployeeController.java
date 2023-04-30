package ourinhos.fatec.det.webapi;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ourinhos.fatec.det.domain.employee.EmployeeService;
import ourinhos.fatec.det.domain.employee.vo.EmployeeVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.employee.EmployeeEntity;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Employee", description = "Serviços que se referem ao funcionário")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create/employee")
    @Transactional
    public ResponseEntity<HttpStatus> createEmployee(
            @RequestBody EmployeeVO employee
    ) {
        return employeeService.createEmployee(employee);
    }


    @GetMapping("/employee/{id}")
    public ResponseEntity<Optional<EmployeeEntity>> getEmployee(@PathVariable("id") String employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @DeleteMapping("/employee/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") String employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> updateEmployee(
            @PathVariable("id") String employeeId,
            @RequestBody EmployeeVO employeeChanged) {
        return employeeService.updateEmployee(employeeChanged, employeeId);
    }


}
