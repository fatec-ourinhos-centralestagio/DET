package ourinhos.fatec.det.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ourinhos.fatec.det.domain.user.UserRepository;
import ourinhos.fatec.det.domain.user.UserService;
import ourinhos.fatec.det.domain.user.vo.UserVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.user.UserEntity;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<HttpStatus> createUser(UserVO user) {
        return userRepository.createUser(user);
    }

    public ResponseEntity<Optional<UserEntity>> getUser(String userId) {
        return userRepository.getUser(userId);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteUser(String userId) {
       return userRepository.deleteUser(userId);
    }

    @Override
    public ResponseEntity<HttpStatus> updateUser(UserVO user, String userId) {
        return userRepository.updateUser(user, userId);
    }
}
