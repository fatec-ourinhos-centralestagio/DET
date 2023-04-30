package ourinhos.fatec.det.webapi;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ourinhos.fatec.det.domain.menu.MenuService;
import ourinhos.fatec.det.domain.menu.vo.MenuVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.items.MenuEntity;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Menu", description = "Serviços que se referem ao menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/create/item")
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<HttpStatus> createItem(
            @RequestBody MenuVO menuVO
    ) {
        return menuService.createItem(menuVO);
    }

    @GetMapping("/item/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<Optional<MenuEntity>> getItem(@PathVariable("id") String itemId) {
        return menuService.getItem(itemId);
    }

    @DeleteMapping("/item/{id}")
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") String itemId) {
        return menuService.deleteItem(itemId);
    }

    @PutMapping("/item/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<HttpStatus> updateItem(
            @PathVariable("id") String itemId,
            @Valid @RequestBody MenuVO menuVO) {
        return menuService.updateItem(menuVO, itemId);
    }


    @GetMapping("/items")
    public ResponseEntity<List<MenuEntity>> getAllItems() {
        return menuService.getAllItem();
    }

}
