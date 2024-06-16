package ru.ivanov.gaming_enjoyment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ivanov.gaming_enjoyment.entities.Platform;

import java.util.List;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {

    @Query("SELECT p FROM Platform p WHERE p.id IN :ids")
    public List<Platform> findAllByIds(List<Integer> ids);
}
