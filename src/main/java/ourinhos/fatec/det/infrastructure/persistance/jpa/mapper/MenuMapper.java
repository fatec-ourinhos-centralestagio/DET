package ourinhos.fatec.det.infrastructure.persistance.jpa.mapper;

import org.springframework.stereotype.Component;
import ourinhos.fatec.det.domain.menu.vo.MenuVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.items.MenuEntity;

@Component
public class MenuMapper {

    public MenuEntity toEntity(MenuVO menuVO, String itemId) {
        return new MenuEntity(
                itemId,
                menuVO.getItem_name(),
                menuVO.getItem_weight(),
                menuVO.getItem_description(),
                menuVO.getItem_energyValue(),
                menuVO.getItem_carbohydrate(),
                menuVO.getItem_protein(),
                menuVO.getItem_totalFat(),
                menuVO.getItem_price()
        );
    }


}
