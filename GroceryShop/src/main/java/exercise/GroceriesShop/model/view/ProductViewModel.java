package exercise.GroceriesShop.model.view;

public class ProductViewModel {
    private Long id;
    private String name;
    private Integer price;

    public ProductViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ProductViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductViewModel setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
