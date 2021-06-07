package ru.geekbrains.controller.repr;

import ru.geekbrains.persist.model.Brand;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ProductRepr implements Serializable {

    private Long id;

    private String name;

    private BigDecimal price;

    private String brand;

    private Long pictureId;

    private List<Long> pictureIds;

    public ProductRepr() {
    }

    public ProductRepr(Long id, String name, BigDecimal price, String brand, Long pictureId, List<Long> pictureIds) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.pictureId = pictureId;
        this.pictureIds = pictureIds;
    }

    public ProductRepr(Long id, String name, BigDecimal price, String brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
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
