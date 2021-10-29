package exercise.GroceriesShop.service;

import exercise.GroceriesShop.model.entity.CategoryDeals;
import exercise.GroceriesShop.model.entity.Deal;
import exercise.GroceriesShop.model.entity.enums.CategoryDealsEnum;

import java.util.List;

public interface CategoryDealService {
    void initCategories();

    CategoryDeals getCategoryByName(CategoryDealsEnum categoryDeals);
    List<Deal> getDealsByCategory(CategoryDealsEnum category);
}
