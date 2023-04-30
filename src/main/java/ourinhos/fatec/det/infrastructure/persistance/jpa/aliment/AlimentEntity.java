package ourinhos.fatec.det.infrastructure.persistance.jpa.aliment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "aliment")
@AllArgsConstructor
@NoArgsConstructor
public class AlimentEntity {

    @Id
    private String id_aliment;

    private String aliment_name;

    private String aliment_weight;

    private String aliment_energyvalue;

    private String aliment_carbohydrate;

    private String aliment_protein;

    private String aliment_totalfat;

    private Double aliment_price;

}
