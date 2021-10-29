package exercise.GroceriesShop.model.entity.enums;

public enum CategoryDealsEnum {
    PAY_2_GET_3 ("Buy 3 for the price of 2"), BUY_1_GET_1_HALF_PRICE ("Buy 1 and get 1 at half price");

    public final String description;

    CategoryDealsEnum(String description) {
        this.description = description;
    }
}
