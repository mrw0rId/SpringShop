package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.PictureData;

import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("select p.contentType from Picture p " +
            "inner join p.pictureData where p.id = :id")
    Optional<String> getContentTypeForBlob(@Param("id") long id);

    @Query("select p.pictureData from Picture p " +
            "inner join p.pictureData where p.id = :id")
    Optional<PictureData> getPictureDataById(@Param("id") long id);

}
