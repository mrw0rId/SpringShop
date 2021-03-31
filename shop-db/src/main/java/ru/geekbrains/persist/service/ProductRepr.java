package ru.geekbrains.persist.service;


import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.model.User;

import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProductRepr {

    private Long id;

    private BigDecimal price;

    private String productName;

    private String description;

    private URL photo;

    @ManyToMany(mappedBy = "products")
    private List<User> users;

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.users = product.getUsers();
        this.photo = product.getPhoto();
    }

    public ProductRepr(BigDecimal price, String productName) {
        this.price = price;
        this.productName = productName;
    }

    public ProductRepr(BigDecimal price, String productName, String description, URL photo) {
        this.price = price;
        this.productName = productName;
        this.description = description;
        this.photo = photo;
    }

    public ProductRepr(BigDecimal price, String productName, String description, URL photo, List<User> users) {
        this.price = price;
        this.productName = productName;
        this.description = description;
        this.users = users;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public URL getPhoto() {
        return photo;
    }

    public void setPhoto(URL photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        List<String> names = new ArrayList<>();
        getUsers().forEach(u->names.add(u.getLogin()));
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", product='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", users=" + names +
                '}';
    }
}
