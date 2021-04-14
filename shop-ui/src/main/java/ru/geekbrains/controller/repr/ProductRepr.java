package ru.geekbrains.controller.repr;

import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.controllers.repr.PictureRepr;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductRepr implements Serializable {

    private Long id;

    private String name;

    private BigDecimal price;

    private Category category;

    private Brand brand;

    private final Long pictureId;

    private final List<Long> pictureIds;

    public ProductRepr(Long id, String name, BigDecimal price,Brand brand, Long pictureId, List<Long> pictureIds) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.pictureId = pictureId;
        this.pictureIds = pictureIds;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public List<Long> getPictureIds() {
        return pictureIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepr that = (ProductRepr) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
