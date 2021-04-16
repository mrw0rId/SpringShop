package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.controller.error.NotFoundException;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductService productService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String productListPage(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> pageSize,
                                  Model model, HttpSession session) {
        logger.info("Products list page");

        Page<ProductRepr> products;

        if (categoryId == null) {
            products = productService.findByFilter(null, page.orElse(1) - 1, pageSize.orElse(9));
        } else if (categoryId == 8) {
            products = productService.findByFilter(null, page.orElse(1) - 1, pageSize.orElse(9));
        } else {
            products = productService.findByFilter(categoryId, page.orElse(1) - 1, pageSize.orElse(9));
        }

//        List<ProductRepr> products;
//        if (categoryId == null) {
//            products = productService.findByFilter(null, page.orElse(1) - 1, pageSize.orElse(9));
//        } else if (categoryId == 8) {
//            products = productService.findByFilter(null, page.orElse(1) - 1, pageSize.orElse(9));
//        } else {
//            products = productService.findByFilter(categoryId, page.orElse(1) - 1, pageSize.orElse(9));
//        }
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryRepository.findAll());

        return "products-list";
    }

    @GetMapping("/{id}")
    public String productPage(Model model, @PathVariable("id") Long id) {
        logger.info("Product page request");
        ProductRepr product = productService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("product", product);
        model.addAttribute("pictures", product.getPictureIds());
        model.addAttribute("pictures2", product.getPictureIds());
        return "product-details";
    }
}
