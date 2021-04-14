package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    Optional<ProductRepr> findById(Long id);

    Page<ProductRepr> findByFilter(Long categoryId, Integer page, Integer pageSize);


}
