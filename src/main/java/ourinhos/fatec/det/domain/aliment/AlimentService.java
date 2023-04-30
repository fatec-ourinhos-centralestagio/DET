package ourinhos.fatec.det.domain.aliment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ourinhos.fatec.det.domain.aliment.vo.AlimentVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.aliment.AlimentEntity;

import java.util.List;
import java.util.Optional;

public interface AlimentService {

    ResponseEntity<HttpStatus> createIngredient(AlimentVO alimentVO);

    ResponseEntity<Optional<AlimentEntity>> getIngredient (String ingredientId);

    ResponseEntity<HttpStatus> deleteIngredient (String ingredientId);

    ResponseEntity<HttpStatus> updateIngredient(AlimentVO alimentVO, String ingredientId);


    ResponseEntity<List<AlimentEntity>> getAllAliments();

}
