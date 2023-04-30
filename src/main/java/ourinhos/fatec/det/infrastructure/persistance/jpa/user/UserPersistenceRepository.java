package ourinhos.fatec.det.infrastructure.persistance.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPersistenceRepository extends JpaRepository<UserEntity,String> {

    Optional<UserEntity> findByEmail(String cpf);
}
