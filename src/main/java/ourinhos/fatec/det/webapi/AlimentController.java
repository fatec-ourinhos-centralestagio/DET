package ourinhos.fatec.det.webapi;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ourinhos.fatec.det.domain.aliment.AlimentService;
import ourinhos.fatec.det.domain.aliment.vo.AlimentVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.aliment.AlimentEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Alimentos", description = "Serviços que se referem a Alimentos")
public class AlimentController {

    @Autowired
    private AlimentService alimentService;

    @PreAuthorize(value = "hasPermission('MANAGER')")
    @PostMapping("/create/aliment")
    @Transactional
    public ResponseEntity<HttpStatus> createIngredient(
            @RequestBody AlimentVO alimentIVO
    ) {
        return alimentService.createIngredient(alimentIVO);
    }

    @PreAuthorize(value = "hasPermission('MANAGER')")
    @GetMapping("/aliment/{id}")
    public ResponseEntity<Optional<AlimentEntity>> getIngredient(@PathVariable("id") String alimentId) {
        return alimentService.getIngredient(alimentId);
    }

    @PreAuthorize(value = "hasPermission('MANAGER')")
    @DeleteMapping("/aliment/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteIngredient(@PathVariable("id") String alimentId) {
        return alimentService.deleteIngredient(alimentId);
    }

    @PreAuthorize(value = "hasPermission('MANAGER'")
    @PutMapping("/aliment/{id}")
    public ResponseEntity<HttpStatus> updateAliment(
            @PathVariable("id") String alimentId,
            @RequestBody AlimentVO alimentVO) {
        return alimentService.updateIngredient(alimentVO, alimentId);
    }

    @GetMapping("/aliments")
    public ResponseEntity<List<AlimentEntity>> getAllAliments() {
        return alimentService.getAllAliments();
    }


}
