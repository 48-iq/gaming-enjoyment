package ru.ivanov.gaming_enjoyment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ivanov.gaming_enjoyment.entities.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p JOIN p.usersNotViewed u WHERE u.id = :userId")
    Page<Post> findPostsByUserId(Integer userId, Pageable pageable);

}
