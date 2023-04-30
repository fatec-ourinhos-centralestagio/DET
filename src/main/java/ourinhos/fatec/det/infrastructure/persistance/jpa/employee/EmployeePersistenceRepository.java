package ourinhos.fatec.det.infrastructure.persistance.jpa.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ourinhos.fatec.det.infrastructure.persistance.jpa.user.UserEntity;

import java.util.Optional;

@Repository
public interface EmployeePersistenceRepository extends JpaRepository<EmployeeEntity,String> {

    Optional<EmployeeEntity> findByEmail(String email);
}
