package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.error.NotFoundException;
import ru.geekbrains.controller.repr.CartItemRepr;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.model.LineItem;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    public final CartService cartService;

    public final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String mainPage(Model model) {
        logger.info("Cart page");
        model.addAttribute("lineItems", cartService.getLineItems());

        List<BigDecimal> collect = cartService.getLineItems().stream().map(LineItem::getTotal).collect(Collectors.toList());
        BigDecimal total = collect.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("total", total);
        return "shopping-cart";
    }

    @PostMapping
    public String addToCart(@RequestParam(value = "productId") Long id,
                            @RequestParam(value = "color", defaultValue = "white") String color,
                            @RequestParam(value = "size", defaultValue = "M") String size,
                            @RequestParam(value = "qty", defaultValue = "1") Integer qty,
                            Model model, HttpServletRequest req) {
        logger.info("Adding product to cart");
        ProductRepr productRepr = productService.findById(id)
                .orElseThrow(NotFoundException::new);
        cartService.addProductQty(productRepr, color, size, qty);
        return "redirect:" + req.getHeader("Referer");
    }

    @PostMapping("/adjust")
    public String adjustQty(@RequestParam(value = "productId") Long id,
                            @RequestParam(value = "color") String color,
                            @RequestParam(value = "size") String size,
                            @RequestParam(value = "qty") Integer qty,
                            Model model){
        logger.info("Adjusting product quantity in cart");
        ProductRepr productRepr = productService.findById(id)
                .orElseThrow(NotFoundException::new);
        cartService.adjustQty(productRepr, color, size, qty);
        return "redirect:/cart";
    }

    @DeleteMapping()
    public String deleteFromCart(@RequestParam(value = "productId") Long id,
                                 @RequestParam(value = "color") String color,
                                 @RequestParam(value = "size") String size,
                                 @RequestParam(value = "qty") Integer qty,
                                 Model model) {
        logger.info("Removing product from cart");
        ProductRepr productRepr = productService.findById(id)
                .orElseThrow(NotFoundException::new);
        cartService.removeProductQty(productRepr, color, size, qty);
        return "redirect:/cart";
    }
}
