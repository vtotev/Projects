package exercise.GroceriesShop.service.impl;

import exercise.GroceriesShop.model.entity.Deal;
import exercise.GroceriesShop.model.entity.ShoppingCart;
import exercise.GroceriesShop.model.entity.enums.CategoryDealsEnum;
import exercise.GroceriesShop.model.service.ProductServiceModel;
import exercise.GroceriesShop.model.view.ProductViewModel;
import exercise.GroceriesShop.repository.ShoppingCartRepository;
import exercise.GroceriesShop.service.CategoryDealService;
import exercise.GroceriesShop.service.ProductService;
import exercise.GroceriesShop.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CategoryDealService categoryDealService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ProductService productService, ModelMapper modelMapper, CategoryDealService categoryDealService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.categoryDealService = categoryDealService;
    }

    @Override
    public void buyProduct(Long id) {
        ProductServiceModel productById = productService.getProductById(id);

        shoppingCartRepository.save(modelMapper.map(productById, ShoppingCart.class));
    }

    @Override
    public void cancelBuyingProduct(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public List<ProductViewModel> getAllProductsInCart() {
        return shoppingCartRepository.findAll().stream().map(shoppingCart -> modelMapper.map(shoppingCart, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getTotalSum() {
        return shoppingCartRepository.findAll().stream().mapToInt(ShoppingCart::getPrice).sum() - getCheapestProduct() - getHalfPriceProduct();
    }

    @Override
    public void buyAllItems() {
        shoppingCartRepository.deleteAll();
    }


    private Integer getCheapestProduct() {
        List<Deal> dealsByCategory = categoryDealService.getDealsByCategory(CategoryDealsEnum.PAY_2_GET_3);
        List<ShoppingCart> cartAll = shoppingCartRepository.findAll();

        int prodCount = 0;
        Integer cheapestProductPrice = Integer.MAX_VALUE;

        for (ShoppingCart prod : cartAll) {

            for (Deal deal : dealsByCategory) {
                if (prod.getName().toLowerCase().equals(deal.getName().toLowerCase())) {
                    prodCount++;
                    Integer prodPrice = prod.getPrice();
                    if (prodPrice < cheapestProductPrice) {
                        cheapestProductPrice = prodPrice;
                    }
                }
            }

            if (prodCount == 3) {
                break;
            }
        }
        if (prodCount < 3) {
            return 0;
        }
        return cheapestProductPrice;
    }

    private Integer getHalfPriceProduct() {
        List<Deal> dealsByCategory = categoryDealService.getDealsByCategory(CategoryDealsEnum.BUY_1_GET_1_HALF_PRICE);
        List<ShoppingCart> cartAll = shoppingCartRepository.findAll();

        int prodCount = 0;
        Integer cheapestProductPrice = Integer.MAX_VALUE;
        for (ShoppingCart prod : cartAll) {
            for (Deal deal : dealsByCategory) {
                if (prod.getName().toLowerCase().equals(deal.getName().toLowerCase())) {
                    prodCount++;
                    Integer prodPrice = prod.getPrice();
                    if (prodPrice < cheapestProductPrice) {
                        cheapestProductPrice = prodPrice;
                    }
                }
            }
            if (prodCount == 2) {
                break;
            }
        }
        if (prodCount < 2) {
            return 0;
        }

        return cheapestProductPrice / 2;
    }

}
