package ourinhos.fatec.det.infrastructure.persistance.jpa.mapper;

import org.springframework.stereotype.Component;
import ourinhos.fatec.det.domain.user.vo.UserVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.user.UserEntity;

@Component
public class UserMapper {

    public UserEntity toEntity(UserVO userVO, String userId){
        return new UserEntity(
          userId,
          userVO.getCpf(),
          userVO.getName(),
          userVO.getAddress(),
          userVO.getPhone(),
          userVO.getEmail(),
          userVO.getPassword()
        );
    }

}
