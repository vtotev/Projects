package exercise.GroceriesShop.service.impl;

import exercise.GroceriesShop.model.binding.DealAddBindingModel;
import exercise.GroceriesShop.model.entity.Deal;
import exercise.GroceriesShop.repository.DealRepository;
import exercise.GroceriesShop.service.CategoryDealService;
import exercise.GroceriesShop.service.DealService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final CategoryDealService categoryDealService;
    private final ModelMapper modelMapper;

    public DealServiceImpl(DealRepository dealRepository, CategoryDealService categoryDealService, ModelMapper modelMapper) {
        this.dealRepository = dealRepository;
        this.categoryDealService = categoryDealService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addDeal(DealAddBindingModel dealAddBindingModel) {
        Deal deal = modelMapper.map(dealAddBindingModel, Deal.class);
        deal.setCategoryDeals(categoryDealService.getCategoryByName(dealAddBindingModel.getCategoryDeals()));
        dealRepository.save(deal);
    }
}
