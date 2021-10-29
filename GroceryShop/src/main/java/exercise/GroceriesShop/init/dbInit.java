package exercise.GroceriesShop.init;

import exercise.GroceriesShop.service.CategoryDealService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class dbInit implements CommandLineRunner {

    private final CategoryDealService categoryDealService;

    public dbInit(CategoryDealService categoryDealService) {
        this.categoryDealService = categoryDealService;
    }


    @Override
    public void run(String... args) throws Exception {
        categoryDealService.initCategories();
    }
}
