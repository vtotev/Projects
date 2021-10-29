package exercise.GroceriesShop.service;

import exercise.GroceriesShop.model.view.ProductViewModel;

import java.util.List;

public interface ShoppingCartService {
    List<ProductViewModel> getAllProductsInCart();
    void buyProduct(Long id);
    void cancelBuyingProduct(Long id);
    Integer getTotalSum();
    void buyAllItems();
}
