package ourinhos.fatec.det.domain.menu.vo;

import lombok.Data;

@Data
public class MenuVO {

    private String item_name;

    private String item_description;

    private String item_weight;

    private String item_energyValue;

    private String item_carbohydrate;

    private String item_protein;

    private String item_totalFat;

    private Double item_price;

}
