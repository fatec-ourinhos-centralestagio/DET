package ourinhos.fatec.det.infrastructure.persistance.jpa.mapper;

import org.springframework.stereotype.Component;
import ourinhos.fatec.det.domain.aliment.vo.AlimentVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.aliment.AlimentEntity;

@Component
public class AlimentMapper {

    public AlimentEntity toEntity(AlimentVO alimentVO, String ingredientVO){
        return new AlimentEntity(
          ingredientVO,
          alimentVO.getAliment_name(),
          alimentVO.getAliment_weight(),
          alimentVO.getAliment_energyValue(),
          alimentVO.getAliment_totalFat(),
          alimentVO.getAliment_carbohydrate(),
          alimentVO.getAliment_protein(),
          alimentVO.getAliment_price()
        );
    }

}
