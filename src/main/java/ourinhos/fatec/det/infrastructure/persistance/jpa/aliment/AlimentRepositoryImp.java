package ourinhos.fatec.det.infrastructure.persistance.jpa.aliment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ourinhos.fatec.det.domain.aliment.AlimentRepository;
import ourinhos.fatec.det.domain.aliment.vo.AlimentVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.items.MenuEntity;
import ourinhos.fatec.det.infrastructure.persistance.jpa.mapper.AlimentMapper;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AlimentRepositoryImp implements AlimentRepository {

    Logger logger = LoggerFactory.getLogger(AlimentRepositoryImp.class);

    @Autowired
    AlimentPersistenceRepository alimentPersistenceRepository;

    @Autowired
    AlimentMapper alimentMapper;

    @Override
    public ResponseEntity<HttpStatus> createIngredient(AlimentVO alimentVO) {
        try {
            AlimentEntity alimentEntity = alimentMapper.toEntity(alimentVO, generateId());
            alimentPersistenceRepository.saveAndFlush(alimentEntity);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alimentEntity.getId_aliment()).toUri();
            logger.info("Ingredient Successfully Saved");
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            logger.info("Error saving ingredient");
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Optional<AlimentEntity>> getIngredient(String ingredientId) {

        Optional<AlimentEntity> ingredient = findById(ingredientId);

        if (ingredient.isEmpty()) {
            logger.info("Ingredient not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredient);
    }

    public Optional<AlimentEntity> findById(String ingredientId) {
        return alimentPersistenceRepository.findById(ingredientId);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteIngredient(String ingredientId) {
        try {
            if (findById(ingredientId).isEmpty()) {
                logger.info("Ingredient not found");
                return ResponseEntity.notFound().build();
            }
            alimentPersistenceRepository.deleteById(ingredientId);
            logger.info("Ingredient Successfully Deleted");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.info("Error deleting ingredient");
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<HttpStatus> updateIngredient(AlimentVO alimentVO, String ingredientId) {
        try {
            if (findById(ingredientId).isEmpty()) {
                logger.info("Ingredient not found");
                return ResponseEntity.notFound().build();
            }
            alimentPersistenceRepository.saveAndFlush(alimentMapper.toEntity(alimentVO, ingredientId));
            logger.info("Ingredient Successfully Updated");
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            logger.info("Error updating ingredients");
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<AlimentEntity>> getAllAliments() {
        try {
            return ResponseEntity.ok(alimentPersistenceRepository.findAll());
        } catch (Exception e) {
            logger.info("Error getting all Items");
            return ResponseEntity.internalServerError().build();
        }
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

}
