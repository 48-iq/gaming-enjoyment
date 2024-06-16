package ru.ivanov.gaming_enjoyment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ivanov.gaming_enjoyment.entities.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query("SELECT g FROM Genre g WHERE CAST(g.id AS INTEGER) IN :ids")
    public List<Genre> findAllByIds(List<Integer> ids);
}
