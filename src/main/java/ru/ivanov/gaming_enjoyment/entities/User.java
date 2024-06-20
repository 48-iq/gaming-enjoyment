package ru.ivanov.gaming_enjoyment.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.ivanov.gaming_enjoyment.enums.Role;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;
    private String status;
    @Lob
    private String image;
    private Role role;

    @ManyToMany
    @JoinTable(name = "user_friend",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    List<User> friends;

    @ManyToMany
    @JoinTable(name = "user_theme",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id", referencedColumnName = "id")
    )
    List<Theme> favoriteThemes;

    @ManyToMany
    @JoinTable(name = "user_game_played",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id")
    )
    List<Game> gamesPlayed;

    @ManyToMany
    @JoinTable(name = "user_game_playing",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id")
    )
    List<Game> gamesPlaying;


    @ManyToMany(mappedBy = "users")
    List<Group> groups;

    @ManyToMany
    @JoinTable(name = "user_theme",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id", referencedColumnName = "id")
    )
    List<Theme> themes;

    @OneToMany(mappedBy = "creator")
    private List<Group> createdGroups;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @ManyToMany
    @JoinTable(name = "user_not_viewed_posts",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id")
    )
    private List<Post> notViewedPosts;

}
