package ourinhos.fatec.det.domain.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserVO {

    private String cpf;

    private String name;

    private String dateOfBirth;

    private String address;

    private String phone;

    private String email;

    private String password;

}
