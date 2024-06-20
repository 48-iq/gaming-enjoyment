package ru.ivanov.gaming_enjoyment.converters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.ivanov.gaming_enjoyment.dto.UserDto;
import ru.ivanov.gaming_enjoyment.entities.Game;
import ru.ivanov.gaming_enjoyment.entities.Group;
import ru.ivanov.gaming_enjoyment.entities.Theme;
import ru.ivanov.gaming_enjoyment.entities.User;
import ru.ivanov.gaming_enjoyment.enums.Role;

import java.util.ArrayList;

@Component
@Slf4j
public class UserConverter {
    public UserDto convertToDto(User user) {
        if (user.getGamesPlayed() == null)
            user.setGamesPlayed(new ArrayList<>());
        if (user.getGamesPlaying() == null)
            user.setGamesPlaying(new ArrayList<>());
        if (user.getGroups() == null)
            user.setGroups(new ArrayList<>());
        if (user.getThemes() == null)
            user.setThemes(new ArrayList<>());
        if (user.getFriends() == null)
            user.setFriends(new ArrayList<>());
        if (user.getCreatedGroups() == null)
            user.setCreatedGroups(new ArrayList<>());
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole() == null ? null : user.getRole().toString())
                .status(user.getStatus())
                .gamesPlayed(user.getGamesPlayed().stream().map(Game::getId).toList())
                .gamesPlaying(user.getGamesPlaying().stream().map(Game::getId).toList())
                .groups(user.getGroups().stream().map(Group::getId).toList())
                .themes(user.getThemes().stream().map(Theme::getId).toList())
                .friends(user.getFriends().stream().map(User::getId).toList())
                .createdGroups(user.getCreatedGroups().stream().map(Group::getId).toList())
                .image(user.getImage())
                .status(user.getStatus())
                .build();
    }

    public User convertToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .image(userDto.getImage())
                .role(Role.valueOf(userDto.getRole()))
                .status(userDto.getStatus())
                .build();
    }
}
