package trevo.agro.api.product;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import trevo.agro.api.area.Area;
import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.image.Image;
import trevo.agro.api.repository.CategoryRepository;
import trevo.agro.api.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    @Valid
    @Length(max = 50)
    private String name;
    @Column(name = "description", columnDefinition = "Text", nullable = false)
    private String description;
    @Column(name = "date")
    private LocalDate date;
    @ManyToMany
    @JoinTable
            (
                    name = "TB_PRODUCT_AREA",
                    joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "area_id", referencedColumnName = "id")}
            )
    private List<Area> areas;

    @ManyToMany
    @JoinTable
            (
            name = "TB_PRODUCT_CATEGORY",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
            )

    private List<Category> categories;
    @ManyToMany
    @JoinTable(
            name = "TB_PRODUCT_CULTURE",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "culture_id", referencedColumnName = "id")}
                )
    private List<Culture> cultures;
    @OneToMany
    @JoinTable(
            name = "TB_PRODUCT_IMAGE",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id", referencedColumnName = "id")}
    )
    @JsonIgnoreProperties({"imageData","type"})
    private List<Image> images;
    private Boolean active;

    public Product(ProductSaveDTO dto,List<Area> areas, List<Category> categories, List<Culture> cultures, List<Image> images) {
        this.name = dto.name();
        this.description = dto.description();
        this.date = LocalDate.now();
        this.areas = areas;
        this.categories = categories;
        this.cultures = cultures;
        this.images = images;
        this.active = true;
    }

    public Product(ProductSaveDTO dto) {
        this.name = dto.name();
        this.description = dto.description();
        this.date = LocalDate.now();
        this.active = true;
    }

    public void update(ProductSaveDTO dto, List<Category> categories, List<Culture> cultures,List<Image> images,List<Area> areas) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.description() != null) {
            this.description = dto.description();
        }
        if (categories != null) {
            this.categories = categories;
        }
        if (cultures != null) {
            this.cultures = cultures;
        }
        if (images != null) {
            this.images = images;
        }
        if (areas != null) {
            this.areas = areas;
        }

    }
}

