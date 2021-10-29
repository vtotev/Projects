package exercise.GroceriesShop.repository;

import exercise.GroceriesShop.model.entity.CategoryDeals;
import exercise.GroceriesShop.model.entity.enums.CategoryDealsEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDealsRepository extends JpaRepository<CategoryDeals, Long> {
    Optional<CategoryDeals> findByName(CategoryDealsEnum name);
}
