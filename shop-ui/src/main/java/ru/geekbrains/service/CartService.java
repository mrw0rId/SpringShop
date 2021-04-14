package ru.geekbrains.service;

import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.util.List;

public interface CartService {

    void addProductQty(ProductRepr productRepr, String color, String size, int qty);

    void adjustQty(ProductRepr productRepr, String color, String size, int qty);

    void removeProductQty(ProductRepr productRepr, String color, String size, int qty);

    List<LineItem> getLineItems();
}
