package exercise.GroceriesShop.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "deals")
public class Deal extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    private CategoryDeals categoryDeals;

    public Deal() {
    }

    public String getName() {
        return name;
    }

    public Deal setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryDeals getCategoryDeals() {
        return categoryDeals;
    }

    public Deal setCategoryDeals(CategoryDeals categoryDeals) {
        this.categoryDeals = categoryDeals;
        return this;
    }


}
