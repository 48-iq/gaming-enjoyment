package ru.ivanov.gaming_enjoyment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ivanov.gaming_enjoyment.entities.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("SELECT g FROM Group g WHERE g.id IN :ids")
    List<Group> findAllByIds(List<Integer> ids);

    @Query("SELECT g FROM Group g WHERE LOWER(g.title) LIKE %:title%")
    Page<Group> findAllByTitleLike(String title, Pageable pageable);

    @Query("SELECT g FROM Group g JOIN g.themes t WHERE t.id IN :themeIds GROUP BY g HAVING COUNT(t.id) >= :themesCount")
    Page<Group> findAllByThemeIds(@Param("themeIds") List<Integer> themeIds,@Param("themesCount") Integer themesCount, Pageable pageable);

}
