package ourinhos.fatec.det.domain.employee.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
public class EmployeeVO {

    private String id;

    private String cpf;

    private String name;

    private String dateofbirth;

    private String address;

    private String phone;

    private String email;

    private String employee_password;

    private Float wage;

    private String office;


}
