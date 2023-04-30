package ourinhos.fatec.det.domain.menu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ourinhos.fatec.det.domain.menu.vo.MenuVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.items.MenuEntity;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    ResponseEntity<HttpStatus> createItem(MenuVO item);

    ResponseEntity<Optional<MenuEntity>> getItem(String itemId);

    ResponseEntity<HttpStatus> deleteItem(String itemId);

    ResponseEntity<HttpStatus> updateItem(MenuVO item, String itemId);

    ResponseEntity<List<MenuEntity>> getAllItem();


}
