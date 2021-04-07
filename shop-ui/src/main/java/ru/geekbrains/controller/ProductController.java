package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.controller.error.NotFoundException;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.ProductService;

@Controller
@RequestMapping
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productListPage(Model model) {
        logger.info("Products list page");
        model.addAttribute("products", productService.findAll());
        return "products-list";
    }

    @GetMapping("/{id}")
    public String productPage(Model model, @PathVariable("id") Long id) {
        logger.info("Product page request");
        ProductRepr product = productService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("product", product);
        model.addAttribute("pictures",product.getPictures());
        model.addAttribute("pictures2",product.getPictures());
        return "product-details";
    }
}
