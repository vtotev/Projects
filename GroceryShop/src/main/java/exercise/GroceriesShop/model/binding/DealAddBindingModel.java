package exercise.GroceriesShop.model.binding;

import exercise.GroceriesShop.model.entity.enums.CategoryDealsEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DealAddBindingModel {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Category must be selected")
    private CategoryDealsEnum categoryDeals;

    public DealAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public DealAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryDealsEnum getCategoryDeals() {
        return categoryDeals;
    }

    public DealAddBindingModel setCategoryDeals(CategoryDealsEnum categoryDeals) {
        this.categoryDeals = categoryDeals;
        return this;
    }
}
