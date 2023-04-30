package ourinhos.fatec.det.infrastructure.persistance.jpa.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ourinhos.fatec.det.domain.employee.EmployeeRepository;
import ourinhos.fatec.det.domain.employee.vo.EmployeeVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.mapper.EmployeeMapper;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EmployeeRepositoryImp implements EmployeeRepository {

    Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImp.class);

    @Autowired
    EmployeePersistenceRepository employeePersistenceRepository;


    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public ResponseEntity<HttpStatus> createEmployee(EmployeeVO employee) {
        try {
            EmployeeEntity employeeEntity = employeeMapper.toEntity(employee, generateId());
            employeePersistenceRepository.save(employeeEntity);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeEntity.getId()).toUri();
            logger.info("Employee Successfully Saved");
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            logger.info("Error saving employee");
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Optional<EmployeeEntity>> getEmployee(String employeeId) {

        Optional<EmployeeEntity> employee = findById(employeeId);

        if (employee.isEmpty()) {
            logger.info("Employee not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }

    public Optional<EmployeeEntity> findById(String employeeId) {
        return employeePersistenceRepository.findById(employeeId);
    }

    public Optional<EmployeeEntity> findByEmail(String employeeId) {
        return employeePersistenceRepository.findByEmail(employeeId);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteEmployee(String employeeId) {
        try {
            if (findById(employeeId).isEmpty()) {
                logger.info("Employee not found");
                return ResponseEntity.notFound().build();
            }
            employeePersistenceRepository.deleteById(employeeId);
            logger.info("Employee Successfully Deleted");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.info("Error deleting employee");
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @Override
    public ResponseEntity<HttpStatus> updateEmployee(EmployeeVO employee, String employeeId) {
        try {
            if (findById(employeeId).isEmpty()) {
                logger.info("Employee not found");
                return ResponseEntity.notFound().build();
            }
            employeePersistenceRepository.saveAndFlush(employeeMapper.toEntity(employee, employeeId));
            logger.info("Employee Successfully Updated");
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            logger.info("Error updating employee");
            return ResponseEntity.internalServerError().build();
        }
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

}
