package ru.ivanov.gaming_enjoyment.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "themes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;

    @ManyToMany(mappedBy = "themes")
    private List<Group> groups;

    @ManyToMany(mappedBy = "themes")
    private List<User> users;
}
