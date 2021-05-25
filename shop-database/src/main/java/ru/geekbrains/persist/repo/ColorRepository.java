package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.Colors;

public interface ColorRepository extends JpaRepository<Colors, Long> {
}
