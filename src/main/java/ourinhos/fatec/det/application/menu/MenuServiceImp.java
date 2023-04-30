package ourinhos.fatec.det.application.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ourinhos.fatec.det.domain.menu.MenuRepository;
import ourinhos.fatec.det.domain.menu.MenuService;
import ourinhos.fatec.det.domain.menu.vo.MenuVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.items.MenuEntity;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public ResponseEntity<HttpStatus> createItem(MenuVO item) {
        return menuRepository.createItem(item);
    }

    @Override
    public ResponseEntity<Optional<MenuEntity>> getItem(String itemId) {
        return menuRepository.getItem(itemId);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteItem(String itemId) {
        return menuRepository.deleteItem(itemId);
    }

    @Override
    public ResponseEntity<HttpStatus> updateItem(MenuVO item, String itemId) {
        return menuRepository.updateItem(item, itemId);
    }

    @Override
    public ResponseEntity<List<MenuEntity>> getAllItem() {
       return menuRepository.getAllItem();
    }

}
