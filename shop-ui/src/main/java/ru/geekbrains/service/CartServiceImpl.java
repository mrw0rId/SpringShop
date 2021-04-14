package ru.geekbrains.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

    private final Map<LineItem, Integer> lineItems = new ConcurrentHashMap<>();

    @Override
    public void addProductQty(ProductRepr productRepr, String color, String size, int qty) {
        LineItem lineItem = new LineItem(productRepr, color, size);
        lineItems.put(lineItem, lineItems.getOrDefault(lineItem, 0) + qty);
    }

    @Override
    public void adjustQty(ProductRepr productRepr, String color, String size, int adjQty) {
        LineItem lineItem = new LineItem(productRepr, color, size);
        int qty = lineItems.get(lineItem);
        int diff = adjQty - qty;
        if (adjQty == 0) {
            lineItems.remove(lineItem);
        }else lineItems.put(lineItem, lineItems.getOrDefault(lineItem, 0) + diff);
    }

    @Override
    public void removeProductQty(ProductRepr productRepr, String color, String size, int qty) {
        LineItem lineItem = new LineItem(productRepr, color, size);
        int currentQty = lineItems.getOrDefault(lineItem, 0);
        if (currentQty - qty > 0) {
            lineItems.put(lineItem, currentQty - qty);
        } else {
            lineItems.remove(lineItem);
        }
    }

    @Override
    public List<LineItem> getLineItems() {
        lineItems.forEach(LineItem::setQty);
        return new ArrayList<>(lineItems.keySet());
    }
}
