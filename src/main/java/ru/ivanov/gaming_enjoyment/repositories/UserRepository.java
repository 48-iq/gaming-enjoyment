package ru.ivanov.gaming_enjoyment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ivanov.gaming_enjoyment.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.id IN :ids")
    public List<User> findAllByIds(List<Integer> ids);
    public boolean existsByUsername(String username);
    public Optional<User> findByUsername(String username);

    @Modifying
    @Query(value = "DELETE FROM users_game_played WHERE user_id = :userId;" +
            "DELETE FROM users_game_playing WHERE user_id = :userId;" +
            "DELETE FROM user_friend WHERE user_id = :userId;" +
            "DELETE FROM user_theme WHERE user_id = :userId;" +
            "DELETE FROM users  WHERE id = :userId;", nativeQuery = true)
    public void deleteUserById(@Param("userId") Integer userId);

}
