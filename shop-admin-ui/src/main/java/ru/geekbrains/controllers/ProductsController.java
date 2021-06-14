package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.service.ProductCategoryBrandFacade;


@Controller
public class ProductsController {

    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    private final ProductCategoryBrandFacade facade;

    @Autowired
    public ProductsController(ProductCategoryBrandFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/products")
    public String adminProductsPage(Model model) {
        logger.info("Facade: find AllProducts");
        facade.findAll(model);
        return "products";
    }

    @GetMapping("/product/{id}/edit")
    public String adminEditProduct(Model model, @PathVariable("id") Long id) {
        logger.info("Facade: find Product with id:" + id);
        facade.editProduct(model, id);
        return "product_form";
    }

    @DeleteMapping("/product/{id}/delete")
    public String adminDeleteProduct(Model model, @PathVariable("id") Long id) {
        logger.info("Facade: delete Product with id:" + id);
        facade.deleteProduct(model, id);
        return "redirect:/products";
    }

    @GetMapping("/product/create")
    public String adminCreateProduct(Model model) {
        logger.info("Facade: create new Product");
        facade.createProduct(model);
        return "product_form";
    }

    @PostMapping("/product")
    public String adminInsertProduct(Model model, RedirectAttributes redirectAttributes, ProductRepr product) {
        logger.info("Facade: save created Product");
        return facade.insertProduct(model, redirectAttributes, product);
    }
}
