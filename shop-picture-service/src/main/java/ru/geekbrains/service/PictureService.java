package ru.geekbrains.service;

import ru.geekbrains.persist.model.PictureData;
import ru.geekbrains.persist.model.Product;

import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<PictureData> getPictureDataById(long id);

    PictureData createPictureData(byte[] picture, String filename);

    Optional<Product> getProductByPictureId(long id);

    void removePicture(long id);
}
