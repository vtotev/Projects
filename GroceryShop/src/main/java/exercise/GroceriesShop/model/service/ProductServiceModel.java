package exercise.GroceriesShop.model.service;

public class ProductServiceModel {
    private String name;
    private Integer price;

    public ProductServiceModel() {
    }

    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductServiceModel setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
