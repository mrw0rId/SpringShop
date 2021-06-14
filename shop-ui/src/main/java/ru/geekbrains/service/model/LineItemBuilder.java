package ru.geekbrains.service.model;

import ru.geekbrains.controller.repr.ProductRepr;

public class LineItemBuilder {

    private final LineItem lineItem;

    public LineItemBuilder (ProductRepr productRepr) {
        this.lineItem = new LineItem(productRepr);
    }

    public LineItemBuilder color(String color) {
        lineItem.setColor(color);
        return this;
    }

    public LineItemBuilder qty(int qty) {
        lineItem.setQty(qty);
        return this;
    }

    public LineItemBuilder size(String size) {
        lineItem.setSize(size);
        return this;
    }

    public LineItem build() {
        return lineItem;
    }
}
