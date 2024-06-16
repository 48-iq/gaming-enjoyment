package ru.ivanov.gaming_enjoyment.services.intrf;

import ru.ivanov.gaming_enjoyment.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAllGenres();

    GenreDto getGenreById(Integer id);

    GenreDto createGenre(GenreDto genreDto);

    GenreDto updateGenre(GenreDto genreDto);

    void deleteGenre(Integer id);
}
