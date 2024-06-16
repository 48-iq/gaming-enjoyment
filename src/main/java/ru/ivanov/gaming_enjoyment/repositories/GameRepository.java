package ru.ivanov.gaming_enjoyment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ivanov.gaming_enjoyment.entities.Game;
import ru.ivanov.gaming_enjoyment.entities.Genre;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query("SELECT g FROM Game g WHERE g.id IN :ids")
    public List<Game> findAllByIds(List<Integer> ids);

    @Query("SELECT g FROM Game g WHERE LOWER(g.title) LIKE %:title%")
    public Page<Game> findAllByTitleLike(String title, Pageable pageable);

    //@Query("SELECT g FROM Game g JOIN g.genres genre WHERE CAST(genre.id AS INTEGER) IN :genresIds")
    //я того #$@?% шатал, столько времени на составление этого запроса ушло
    @Query("SELECT g FROM Game g JOIN g.genres genre WHERE genre.id IN :genresIds GROUP BY g HAVING COUNT(genre.id) >= :genresCount")
    Page<Game> findGamesByGenreIds(@Param("genresIds") List<Integer> genresIds, @Param("genresCount") Integer genresCount, Pageable pageable);

    @Query("SELECT game FROM Game game JOIN game.platforms platform WHERE platform.id IN :platformIds GROUP BY game HAVING COUNT(platform.id) >= :platformsCount")
    public Page<Game> findAllByPlatformIds(@Param("platformIds") List<Integer> platformIds, @Param("platformsCount") Integer platformsCount, Pageable pageable);

}
