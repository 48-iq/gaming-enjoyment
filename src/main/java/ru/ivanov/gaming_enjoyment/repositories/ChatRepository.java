package ru.ivanov.gaming_enjoyment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ivanov.gaming_enjoyment.entities.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query("SELECT c FROM Chat JOIN c.users u WHERE u.id = ?1")
    Page<Chat> getChatsByUserId(Integer id, Pageable query);
}
