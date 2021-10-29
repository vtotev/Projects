package exercise.GroceriesShop.model.entity;

import exercise.GroceriesShop.model.entity.enums.CategoryDealsEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category_deals")
public class CategoryDeals extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CategoryDealsEnum name;

    @OneToMany(mappedBy = "categoryDeals", fetch = FetchType.EAGER)
    private List<Deal> deals;

    public CategoryDeals() {
    }

    public CategoryDealsEnum getName() {
        return name;
    }

    public CategoryDeals setName(CategoryDealsEnum name) {
        this.name = name;
        return this;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public CategoryDeals setDeals(List<Deal> deals) {
        this.deals = deals;
        return this;
    }
}
