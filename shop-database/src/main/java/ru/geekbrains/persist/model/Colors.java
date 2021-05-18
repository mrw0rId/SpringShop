package ru.geekbrains.persist.model;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "colors")
public class Colors implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "color", nullable = false)
    private String color;

    @ManyToMany(mappedBy = "colors")
    private List<Product> products;

    public Colors() {
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
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colors colors = (Colors) o;
        return Objects.equals(id, colors.id) && Objects.equals(name, colors.name) && Objects.equals(color, colors.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }
}
