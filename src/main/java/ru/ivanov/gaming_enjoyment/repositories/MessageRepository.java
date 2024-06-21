package ru.ivanov.gaming_enjoyment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ivanov.gaming_enjoyment.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m FROM Message m JOIN m.chat c WHERE c.id = ?1")
    Page<Message> getMessagesByChatId(Integer id, Pageable query);
}
