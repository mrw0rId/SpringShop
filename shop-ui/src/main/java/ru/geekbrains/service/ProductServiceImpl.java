package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.specification.ProductSpecification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final PictureService pictureService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PictureService pictureService) {
        this.productRepository = productRepository;
        this.pictureService = pictureService;
    }

    @Override
    @Transactional
    public List<ProductRepr> findAll() {
        return productRepository.findAllWithPictureFetch().stream()
                .map(ProductServiceImpl::mapToRepr)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ProductRepr> findById(Long id) {
        return productRepository.findById(id).map(ProductServiceImpl::mapToRepr);
    }

    @Override
    public Page<ProductRepr> findByFilter(Long categoryId, Integer page, Integer pageSize) {
        Specification<Product> spec = Specification.where(null); // = ProductSpecification.fetchPictures();

        if (categoryId != null) {
            spec = spec.and(ProductSpecification.byCategory(categoryId));
        }
        Page<Long> ids = productRepository
                .findAll(spec, PageRequest.of(page, pageSize))
                .map(Product::getId);

        List<ProductRepr> allByIds = productRepository.findAllByIds(ids.getContent())
                .stream()
                .map(ProductServiceImpl::mapToRepr)
                .collect(Collectors.toList());

        return new PageImpl<>(allByIds, PageRequest.of(page, pageSize), ids.getTotalElements());
    }

    private static ProductRepr mapToRepr(Product p) {
        return new ProductRepr(
                p.getId(),
                p.getName(), p.getPrice(),
                p.getBrand().getName(),
                p.getPictures().size() > 0 ? p.getPictures().get(0).getId() : null,
                p.getPictures().stream().map(Picture::getId).collect(Collectors.toList())
        );
    }

}
