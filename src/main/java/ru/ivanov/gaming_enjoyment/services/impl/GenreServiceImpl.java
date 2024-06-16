package ru.ivanov.gaming_enjoyment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.converters.GenreConverter;
import ru.ivanov.gaming_enjoyment.dto.GenreDto;
import ru.ivanov.gaming_enjoyment.entities.Genre;
import ru.ivanov.gaming_enjoyment.exceptions.EntityNotFoundException;
import ru.ivanov.gaming_enjoyment.exceptions.NullIdException;
import ru.ivanov.gaming_enjoyment.repositories.GameRepository;
import ru.ivanov.gaming_enjoyment.repositories.GenreRepository;
import ru.ivanov.gaming_enjoyment.services.intrf.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreConverter genreConverter;
    private final GameRepository gameRepository;

    @Override
    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll()
                .stream().map(genreConverter::convertToDto).toList();
    }

    @Override
    public GenreDto getGenreById(Integer id) {
        return genreRepository.findById(id)
                .map(genreConverter::convertToDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Genre with id " + id + " not found")
                );
    }

    @Override
    public GenreDto createGenre(GenreDto genreDto) {
        Genre genre = genreConverter.convertToEntity(genreDto);
        return genreConverter.convertToDto(genreRepository.save(genre));
    }

    @Override
    public GenreDto updateGenre(GenreDto genreDto) {
        if (genreDto.getId() == null)
            throw new NullIdException("Id must not be null when updating genre");
        Genre genre = genreRepository.findById(genreDto.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Genre with id " + genreDto.getId() + " not found")
                );
        if (genreDto.getDescription() != null) {
            genre.setDescription(genreDto.getDescription());
        }
        if (genreDto.getTitle() != null) {
            genre.setTitle(genreDto.getTitle());
        }
        return genreConverter.convertToDto(genreRepository.save(genre));
    }

    @Override
    public void deleteGenre(Integer id) {
        genreRepository.deleteById(id);
    }
}
