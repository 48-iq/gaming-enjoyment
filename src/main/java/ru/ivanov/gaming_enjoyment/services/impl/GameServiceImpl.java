package ru.ivanov.gaming_enjoyment.services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.converters.GameConverter;
import ru.ivanov.gaming_enjoyment.dto.GameDto;

import ru.ivanov.gaming_enjoyment.entities.Game;
import ru.ivanov.gaming_enjoyment.exceptions.NullIdException;
import ru.ivanov.gaming_enjoyment.queries.*;
import ru.ivanov.gaming_enjoyment.repositories.GameRepository;
import ru.ivanov.gaming_enjoyment.repositories.GenreRepository;
import ru.ivanov.gaming_enjoyment.repositories.PlatformRepository;
import ru.ivanov.gaming_enjoyment.repositories.UserRepository;
import ru.ivanov.gaming_enjoyment.services.intrf.GameService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameConverter gameConverter;
    private final GenreRepository genreRepository;
    private final PlatformRepository platformRepository;

    @Override
    @Transactional
    public GameDto getGameById(Integer id) {
        return gameConverter.convertToDto(
                gameRepository.findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("Game with id " + id + " not found")
                        )
        );
    }

    @Transactional
    @Override
    public Page<GameDto> getAllGames(PageQuery pageQuery) {
        return gameRepository
                .findAll(PageRequest.of(pageQuery.getPage(), pageQuery.getSize()))
                .map(gameConverter::convertToDto);
    }

    @Transactional
    @Override
    public Page<GameDto> getGamesByTitle(GameTitlePageQuery query) {
        query.setTitle(query.getTitle().toLowerCase());
        Page<Game> gamePage = gameRepository.findAllByTitleLike(query.getTitle(),
                PageRequest.of(query.getPage(), query.getSize()));

        return new PageImpl<GameDto>(
                gamePage.getContent().stream()
                        .map(gameConverter::convertToDto)
                        .toList()
                , gamePage.getPageable()
                , gamePage.getTotalElements()
        );
    }

    @Transactional
    @Override
    public Page<GameDto> getGamesByGenres(GameGenrePageQuery query) {
        Page<Game> gamePage = gameRepository.findGamesByGenreIds(query.getGenres(),
                query.getGenres().size(),
                PageRequest.of(query.getPage(), query.getSize()));
        return new PageImpl<GameDto>(
                gamePage.getContent().stream()
                        .map(gameConverter::convertToDto)
                        .toList()
                , gamePage.getPageable()
                , gamePage.getTotalElements()
        );
    }

    @Transactional
    @Override
    public Page<GameDto> getGamesByPlatforms(GamePlatformPageQuery query) {
        Page<Game> gamePage = gameRepository.findAllByPlatformIds(query.getPlatforms(),
                query.getPlatforms().size(),
                PageRequest.of(query.getPage(), query.getSize()));
        return new PageImpl<GameDto>(
                gamePage.getContent().stream()
                        .map(gameConverter::convertToDto)
                        .toList()
                , gamePage.getPageable()
                , gamePage.getTotalElements()
        );
    }

    @Transactional
    @Override
    public Page<GameDto> getGamesUserPlayed(GameUserPageQuery gameUserPageQuery) {
        Page<Game> gamePage = gameRepository.findGamesUserPlayedByUserId(gameUserPageQuery.getUserId(),
                PageRequest.of(gameUserPageQuery.getPage(), gameUserPageQuery.getSize()));
        System.out.println("content----------------------------------------<><><><><><>");
        System.out.println(gamePage.getContent());
        return new PageImpl<GameDto>(
                gamePage.getContent().stream()
                        .map(gameConverter::convertToDto)
                        .toList(),
                gamePage.getPageable(),
                gamePage.getTotalElements()
        );
    }

    @Transactional
    @Override
    public Page<GameDto> getGamesUserPlaying(GameUserPageQuery gameUserPageQuery) {
        Page<Game> gamePage = gameRepository.findGamesUserPlayingByUserId(gameUserPageQuery.getUserId(),
                PageRequest.of(gameUserPageQuery.getPage(), gameUserPageQuery.getSize()));
        return new PageImpl<GameDto>(
                gamePage.getContent().stream()
                        .map(gameConverter::convertToDto)
                        .toList(),
                gamePage.getPageable(),
                gamePage.getTotalElements()
        );
    }

    @Transactional
    @Override
    public GameDto createGame(GameDto gameDto) {
        Game game = gameConverter.convertToEntity(gameDto);
        if (gameDto.getGenres() != null) {
            game.setGenres(
                    genreRepository
                            .findAllByIds(gameDto.getGenres())
            );
        }
        if (gameDto.getPlatforms() != null) {
            game.setPlatforms(
                    platformRepository
                            .findAllByIds(gameDto.getPlatforms())
            );
        }
        game = gameRepository.save(game);
        return gameConverter.convertToDto(game);
    }

    @Transactional
    @Override
    public GameDto updateGame(GameDto gameDto) {
        if (gameDto.getId() == null) {
            throw new NullIdException("Id must not be null when updating game");
        }
        Game game = gameRepository.findById(gameDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Game with id " + gameDto.getId() + " not found"));
        if (gameDto.getTitle() != null) {
            game.setTitle(gameDto.getTitle());
        }
        if (gameDto.getDescription() != null) {
            game.setDescription(gameDto.getDescription());
        }
        if (gameDto.getSystemRequirements() != null) {
            game.setSystemRequirements(gameDto.getSystemRequirements());
        }
        if (gameDto.getGenres() != null) {
            game.setGenres(
                   genreRepository
                            .findAllByIds(gameDto.getGenres())
            );
        }
        if (gameDto.getPlatforms() != null) {
            game.setPlatforms(
                    platformRepository
                            .findAllByIds(gameDto.getPlatforms())
            );
        }
        if (gameDto.getImage() != null) {
            game.setImage(gameDto.getImage());
        }
        game = gameRepository.save(game);
        return gameConverter.convertToDto(game);
    }

    @Transactional
    @Override
    public void deleteGameById(Integer id) {
        gameRepository.deleteGameById(id);
    }
}
