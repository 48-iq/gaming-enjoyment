package ru.ivanov.gaming_enjoyment.converters;

import org.springframework.stereotype.Component;
import ru.ivanov.gaming_enjoyment.dto.GroupDto;
import ru.ivanov.gaming_enjoyment.entities.Game;
import ru.ivanov.gaming_enjoyment.entities.Group;
import ru.ivanov.gaming_enjoyment.entities.Theme;
import ru.ivanov.gaming_enjoyment.entities.User;

import java.util.ArrayList;

@Component
public class GroupConverter {

    public GroupDto convertToDto(Group group) {
        if (group.getThemes() == null)
            group.setThemes(new ArrayList<>());
        if (group.getUsers() == null)
            group.setUsers(new ArrayList<>());
        if (group.getGames() == null)
            group.setGames(new ArrayList<>());
        return GroupDto.builder()
                .id(group.getId())
                .title(group.getTitle())
                .creator(group.getCreator().getId())
                .description(group.getDescription())
                .users(group.getUsers()
                        .stream().map(User::getId).toList())
                .themes(group.getThemes()
                        .stream().map(Theme::getId).toList())
                .games(group.getGames().stream().map(Game::getId).toList())
                .image(group.getImage())
                .build();
    }

    public Group convertToEntity(GroupDto groupDto) {
        return Group.builder()
                .id(groupDto.getId())
                .title(groupDto.getTitle())
                .description(groupDto.getDescription())
                .image(groupDto.getImage())
                .build();
    }

}
