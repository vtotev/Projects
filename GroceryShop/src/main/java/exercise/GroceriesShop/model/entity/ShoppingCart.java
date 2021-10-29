package exercise.GroceriesShop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    public ShoppingCart() {
    }

    public String getName() {
        return name;
    }

    public ShoppingCart setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ShoppingCart setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
