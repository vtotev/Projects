package exercise.GroceriesShop.web;

import exercise.GroceriesShop.service.ProductService;
import exercise.GroceriesShop.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingController(ProductService productService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping({"", "/"})
    public String shopping(Model model) {
        model.addAttribute("allProducts", productService.getAllProducts());
        model.addAttribute("productsToBuy", shoppingCartService.getAllProductsInCart());
        Integer totalSum = shoppingCartService.getTotalSum();
        model.addAttribute("total", String.format("%d aws and %d clouds", totalSum / 100, totalSum % 100));
        return "shopping";
    }

    @GetMapping("/buy/{id}")
    public String addItemToCart(@PathVariable Long id) {
        shoppingCartService.buyProduct(id);
        return "redirect:/shopping/";
    }

    @GetMapping("/cancel/{id}")
    public String removeItemFromCart(@PathVariable Long id) {
        shoppingCartService.cancelBuyingProduct(id);
        return "redirect:/shopping";
    }

    @GetMapping("/buy/all")
    public String buyAllItems() {
        shoppingCartService.buyAllItems();
        return "redirect:/shopping";
    }


}
