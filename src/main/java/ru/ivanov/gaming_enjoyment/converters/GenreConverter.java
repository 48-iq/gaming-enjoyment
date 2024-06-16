package ru.ivanov.gaming_enjoyment.converters;

import org.springframework.stereotype.Component;
import ru.ivanov.gaming_enjoyment.dto.GenreDto;
import ru.ivanov.gaming_enjoyment.entities.Game;
import ru.ivanov.gaming_enjoyment.entities.Genre;

import java.util.ArrayList;

@Component
public class GenreConverter {
    public GenreDto convertToDto(Genre genre) {
        return GenreDto.builder()
                .id(genre.getId())
                .title(genre.getTitle())
                .description(genre.getDescription())
                .build();
    }

    public Genre convertToEntity(GenreDto genreDto) {
        return Genre.builder()
                .id(genreDto.getId())
                .title(genreDto.getTitle())
                .description(genreDto.getDescription())
                .build();
    }
}
