package ourinhos.fatec.det.infrastructure.persistance.jpa.items;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "menu")
@AllArgsConstructor
@NoArgsConstructor
public class MenuEntity {

    @Id
    private String id_menu_item;

    private String item_name;

    private String item_description;

    private String item_weight;

    private String item_energyvalue;

    private String item_carbohydrate;

    private String item_protein;

    private String item_totalfat;

    private Double item_price;

}
