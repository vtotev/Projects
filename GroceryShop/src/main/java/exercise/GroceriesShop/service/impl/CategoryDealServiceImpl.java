package exercise.GroceriesShop.service.impl;

import exercise.GroceriesShop.model.entity.CategoryDeals;
import exercise.GroceriesShop.model.entity.Deal;
import exercise.GroceriesShop.model.entity.enums.CategoryDealsEnum;
import exercise.GroceriesShop.repository.CategoryDealsRepository;
import exercise.GroceriesShop.service.CategoryDealService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryDealServiceImpl implements CategoryDealService {

    private final CategoryDealsRepository categoryDealsRepository;

    public CategoryDealServiceImpl(CategoryDealsRepository categoryDealsRepository) {
        this.categoryDealsRepository = categoryDealsRepository;
    }

    @Override
    public void initCategories() {
        if (categoryDealsRepository.count() == 0)
        Arrays.stream(CategoryDealsEnum.values()).forEach(cat -> {
            CategoryDeals categoryDeals = new CategoryDeals();
            categoryDeals.setName(cat);
            categoryDealsRepository.save(categoryDeals);
        });
    }

    @Override
    public CategoryDeals getCategoryByName(CategoryDealsEnum categoryDeals) {
        return categoryDealsRepository.findByName(categoryDeals).orElse(null);
    }

    @Override
    public List<Deal> getDealsByCategory(CategoryDealsEnum category) {
        return categoryDealsRepository.findByName(category).get().getDeals();
    }
}
