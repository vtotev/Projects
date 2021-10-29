package exercise.GroceriesShop.web;

import exercise.GroceriesShop.model.binding.DealAddBindingModel;
import exercise.GroceriesShop.service.DealService;
import exercise.GroceriesShop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/deals")
public class DealController {

    private final DealService dealService;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    public DealController(DealService dealService, ModelMapper modelMapper, ProductService productService) {
        this.dealService = dealService;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addDeal(Model model) {
        if (!model.containsAttribute("dealAddBindingModel")) {
            model.addAttribute("dealAddBindingModel", new DealAddBindingModel());
        }
        model.addAttribute("products", productService.getAllProducts());
        return "deal-add";
    }

    @PostMapping("/add")
    public String addDealConfirm(@Valid DealAddBindingModel dealAddBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("dealAddBindingModel", dealAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.dealAddBindingModel", bindingResult);
            return "redirect:add";
        }

        dealService.addDeal(dealAddBindingModel);

        return "redirect:add";
    }
}
