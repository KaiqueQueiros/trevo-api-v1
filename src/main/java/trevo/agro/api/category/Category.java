package trevo.agro.api.category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tb_category")
@Entity
/*@SequenceGenerator( name = "Category_seq",
                            sequenceName = "Category_seq",
                            initialValue = 1, allocationSize = 1)*/
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull(message = "Erro! Por favor insira no nome da categoria.")
    @Column(name = "name")
    private String name;


}


