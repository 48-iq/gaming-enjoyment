package ru.ivanov.gaming_enjoyment.converters;

import org.springframework.stereotype.Component;
import ru.ivanov.gaming_enjoyment.dto.GameDto;
import ru.ivanov.gaming_enjoyment.entities.Game;
import ru.ivanov.gaming_enjoyment.entities.Genre;
import ru.ivanov.gaming_enjoyment.entities.Platform;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameConverter {
    public GameDto convertToDto(Game game) {
        if (game.getUsersPlayed() == null)
            game.setUsersPlayed(new ArrayList<>());
        if (game.getUsersPlaying() == null)
            game.setUsersPlaying(new ArrayList<>());
        if (game.getGenres() == null)
            game.setGenres(new ArrayList<>());
        if (game.getPlatforms() == null)
            game.setPlatforms(new ArrayList<>());

        return GameDto.builder()
                .id(game.getId())
                .title(game.getTitle())
                .description(game.getDescription())
                .systemRequirements(game.getSystemRequirements())
                .genres(game.getGenres().stream().map(Genre::getId).toList())
                .platforms(game.getPlatforms().stream().map(Platform::getId).toList())
                .build();
    }

    public Game convertToEntity(GameDto gameDto) {
        return Game.builder()
                .id(gameDto.getId())
                .title(gameDto.getTitle())
                .description(gameDto.getDescription())
                .systemRequirements(gameDto.getSystemRequirements())
                .build();
    }
}
