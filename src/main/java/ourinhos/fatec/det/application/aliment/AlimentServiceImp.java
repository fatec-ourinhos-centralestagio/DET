package ourinhos.fatec.det.application.aliment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ourinhos.fatec.det.domain.aliment.AlimentRepository;
import ourinhos.fatec.det.domain.aliment.AlimentService;
import ourinhos.fatec.det.domain.aliment.vo.AlimentVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.aliment.AlimentEntity;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentServiceImp implements AlimentService {

    @Autowired
    AlimentRepository alimentRepository;

    @Override
    public ResponseEntity<HttpStatus> createIngredient(AlimentVO alimentVO) {
        return alimentRepository.createIngredient(alimentVO);
    }

    @Override
    public ResponseEntity<Optional<AlimentEntity>> getIngredient(String ingredientId) {
        return alimentRepository.getIngredient(ingredientId);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteIngredient(String ingredientId) {
        return alimentRepository.deleteIngredient(ingredientId);
    }

    @Override
    public ResponseEntity<HttpStatus> updateIngredient(AlimentVO alimentVO, String ingredientId) {
        return alimentRepository.updateIngredient(alimentVO, ingredientId);
    }

    @Override
    public ResponseEntity<List<AlimentEntity>> getAllAliments() {
        return alimentRepository.getAllAliments();
    }

}
