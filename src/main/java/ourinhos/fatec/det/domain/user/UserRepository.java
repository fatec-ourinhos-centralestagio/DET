package ourinhos.fatec.det.domain.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ourinhos.fatec.det.domain.user.vo.UserVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.user.UserEntity;

import java.util.Optional;

public interface UserRepository {

    ResponseEntity<HttpStatus> createUser(UserVO user);

    ResponseEntity<Optional<UserEntity>> getUser(String userId);

    ResponseEntity<HttpStatus> deleteUser (String userId);

    ResponseEntity<HttpStatus> updateUser(UserVO user, String userId);
}
