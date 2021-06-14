package ru.geekbrains.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.controllers.repr.ProductRepr;

public interface ProductCategoryBrandFacade {

    void findAll(Model model);

    void editProduct(Model model, Long id);

    void deleteProduct(Model model, Long id);

    void createProduct(Model model);

    String insertProduct(Model model,
                         RedirectAttributes redirectAttributes,
                         ProductRepr product);
}
