package ourinhos.fatec.det.infrastructure.persistance.jpa.items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ourinhos.fatec.det.domain.menu.MenuRepository;
import ourinhos.fatec.det.domain.menu.vo.MenuVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.mapper.MenuMapper;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MenuRepositoryImp implements MenuRepository {

    Logger logger = LoggerFactory.getLogger(MenuRepositoryImp.class);

    @Autowired
    MenuPersistenceRepository menuPersistenceRepository;

    @Autowired
    MenuMapper menuMapper;

    @Override
    public ResponseEntity<HttpStatus> createItem(MenuVO item) {
        try {
            MenuEntity menuEntity = menuMapper.toEntity(item, generateId());
            menuPersistenceRepository.saveAndFlush(menuEntity);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(menuEntity.getId_menu_item()).toUri();
            logger.info("Item Successfully Saved");
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            logger.info("Error saving Item");
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Optional<MenuEntity>> getItem(String itemId) {

        Optional<MenuEntity> item = findById(itemId);

        if (item.isEmpty()) {
            logger.info("Item not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(item);
    }

    public Optional<MenuEntity> findById(String itemId) {
        return menuPersistenceRepository.findById(itemId);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteItem(String itemId) {
        try {
            if (findById(itemId).isEmpty()) {
                logger.info("Item not found");
                return ResponseEntity.notFound().build();
            }
            menuPersistenceRepository.deleteById(itemId);
            logger.info("Item Successfully Deleted");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.info("Error deleting ingredient");
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<HttpStatus> updateItem(MenuVO item, String itemId) {
        try {
            if (findById(itemId).isEmpty()) {
                logger.info("Item not found");
                return ResponseEntity.notFound().build();
            }
            menuPersistenceRepository.saveAndFlush(menuMapper.toEntity(item, itemId));
            logger.info("Item Successfully Updated");
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            logger.info("Error updating ingredients");
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<MenuEntity>> getAllItem() {
        try {
            return ResponseEntity.ok(menuPersistenceRepository.findAll());
        } catch (Exception e) {
            logger.info("Error getting all Items");
            return ResponseEntity.internalServerError().build();
        }
    }


    private String generateId() {
        return UUID.randomUUID().toString();
    }

}
