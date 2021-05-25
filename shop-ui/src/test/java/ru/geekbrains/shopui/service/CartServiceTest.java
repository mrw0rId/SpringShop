package ru.geekbrains.shopui.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.CartServiceImpl;
import ru.geekbrains.service.model.LineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void  init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart() {
        assertNotNull(cartService.getLineItems());
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddProduct() {
        ProductRepr expected = new ProductRepr();
        expected.setId(1L);
        expected.setPrice(new BigDecimal(321));
        expected.setName("Ulala");
        expected.setBrand("UmpaLumpa");
        cartService.addProductQty(expected, "WHITE","M",1);

        List<LineItem> lineItems = cartService.getLineItems();

        assertNotNull(lineItems);
        assertEquals(1,lineItems.size());

        LineItem lineItem = lineItems.get(0);
        assertEquals("WHITE", lineItem.getColor());
        assertEquals("M", lineItem.getSize());
        assertEquals(1, lineItem.getQty());

        assertEquals(expected.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductRepr());
        assertEquals(expected.getName(), lineItem.getProductRepr().getName());
    }

    @Test
    public void testSecondConstrictor() {
        List<LineItem> expectedLineItems = new ArrayList<>();
        ProductRepr expectedProduct1 = new ProductRepr(1L,"aaa", new BigDecimal(123), "A");
        ProductRepr expectedProduct2 = new ProductRepr(2L,"bbb", new BigDecimal(456), "B");
        ProductRepr expectedProduct3 = new ProductRepr(3L,"ccc", new BigDecimal(789), "C");
        LineItem expectedLineItem1 = new LineItem(expectedProduct1, "BLACK", "L", 1);
        LineItem expectedLineItem2 = new LineItem(expectedProduct2, "WHITE", "S", 1);
        LineItem expectedLineItem3 = new LineItem(expectedProduct3, "RED", "M", 1);
        expectedLineItems.add(expectedLineItem1);
        expectedLineItems.add(expectedLineItem2);
        expectedLineItems.add(expectedLineItem3);

        cartService = new CartServiceImpl(expectedLineItems);

        List<LineItem> lineItems = cartService.getLineItems();

        assertNotNull(lineItems);
        assertEquals(3,lineItems.size());
        assertEquals(expectedLineItems, lineItems);
    }
}
