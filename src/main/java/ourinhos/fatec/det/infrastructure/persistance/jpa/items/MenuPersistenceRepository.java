package ourinhos.fatec.det.infrastructure.persistance.jpa.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuPersistenceRepository extends JpaRepository<MenuEntity,String> {

}
