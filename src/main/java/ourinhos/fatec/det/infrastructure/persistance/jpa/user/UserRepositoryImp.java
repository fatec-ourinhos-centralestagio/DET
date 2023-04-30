package ourinhos.fatec.det.infrastructure.persistance.jpa.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ourinhos.fatec.det.domain.user.UserRepository;
import ourinhos.fatec.det.domain.user.vo.UserVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.mapper.UserMapper;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImp implements UserRepository {

    Logger logger = LoggerFactory.getLogger(UserRepositoryImp.class);

    @Autowired
    UserPersistenceRepository userPersistenceRepository;


    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseEntity<HttpStatus> createUser(UserVO user) {
        try {
            UserEntity userEntity = userMapper.toEntity(user, generateId());
            userPersistenceRepository.save(userEntity);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userEntity.getId()).toUri();
            logger.info("User Successfully Saved");
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            logger.info("Error saving user");
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Optional<UserEntity>> getUser(String userId) {

        Optional<UserEntity> user = findById(userId);

        if (user.isEmpty()) {
            logger.info("User not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    public Optional<UserEntity> findById(String userId) {
        return userPersistenceRepository.findById(userId);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteUser(String userId) {
        try {
            if (findById(userId).isEmpty()) {
                logger.info("User not found");
                return ResponseEntity.notFound().build();
            }
            userPersistenceRepository.deleteById(userId);
            logger.info("User Successfully Deleted");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.info("Error deleting user");
            return ResponseEntity.internalServerError().build();
        }
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userPersistenceRepository.findByEmail(email);
    }

    @Override
    public ResponseEntity<HttpStatus> updateUser(UserVO user, String userId) {
        try {
            if (findById(userId).isEmpty()) {
                logger.info("User not found");
                return ResponseEntity.notFound().build();
            }
            userPersistenceRepository.saveAndFlush(userMapper.toEntity(user, userId));
            logger.info("User Successfully Updated");
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            logger.info("Error updating user");
            return ResponseEntity.internalServerError().build();
        }
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

}
