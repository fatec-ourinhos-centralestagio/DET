package ourinhos.fatec.det.infrastructure.persistance.jpa.mapper;

import org.springframework.stereotype.Component;
import ourinhos.fatec.det.domain.employee.vo.EmployeeVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.employee.EmployeeEntity;

@Component
public class EmployeeMapper {

    public EmployeeEntity toEntity(EmployeeVO employeeVO, String employeeId) {
        return new EmployeeEntity(
                employeeId,
                employeeVO.getCpf(),
                employeeVO.getName(),
                employeeVO.getDateofbirth(),
                employeeVO.getAddress(),
                employeeVO.getPhone(),
                employeeVO.getEmail(),
                employeeVO.getEmployee_password(),
                employeeVO.getWage(),
                employeeVO.getOffice()
        );
    }
}
