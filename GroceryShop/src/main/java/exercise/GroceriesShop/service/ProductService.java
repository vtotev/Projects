package exercise.GroceriesShop.service;

import exercise.GroceriesShop.model.entity.Product;
import exercise.GroceriesShop.model.service.ProductServiceModel;
import exercise.GroceriesShop.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    List<ProductViewModel> getAllProducts();

    ProductServiceModel getProductById(Long id);
}
