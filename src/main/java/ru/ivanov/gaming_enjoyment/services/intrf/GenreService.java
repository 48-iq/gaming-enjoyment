package ru.ivanov.gaming_enjoyment.services.intrf;

import ru.ivanov.gaming_enjoyment.dto.GenreDto;
import ru.ivanov.gaming_enjoyment.queries.IdsQuery;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAllGenres();

    GenreDto getGenreById(Integer id);

    GenreDto createGenre(GenreDto genreDto);

    GenreDto updateGenre(GenreDto genreDto);

    List<GenreDto> getGenresByIds(IdsQuery idsQuery);

    void deleteGenre(Integer id);
}
