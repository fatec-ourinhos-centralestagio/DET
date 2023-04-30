package ourinhos.fatec.det.infrastructure.persistance.jpa.aliment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentPersistenceRepository extends JpaRepository<AlimentEntity,String> {
}
