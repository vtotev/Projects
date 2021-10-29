package exercise.GroceriesShop.service.impl;

import exercise.GroceriesShop.model.entity.Product;
import exercise.GroceriesShop.model.service.ProductServiceModel;
import exercise.GroceriesShop.model.view.ProductViewModel;
import exercise.GroceriesShop.repository.ProductRepository;
import exercise.GroceriesShop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductViewModel> getAllProducts() {

        return productRepository.findAll().stream().map(p -> modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return modelMapper.map(product, ProductServiceModel.class);
    }
}
