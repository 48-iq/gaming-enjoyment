package ru.ivanov.gaming_enjoyment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ivanov.gaming_enjoyment.entities.Theme;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    @Query("SELECT t FROM Theme t WHERE t.id IN :ids")
    List<Theme> findAllByIds(List<Integer> ids);
}
