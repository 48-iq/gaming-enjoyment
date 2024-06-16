package ru.ivanov.gaming_enjoyment.services.intrf;


import org.springframework.data.domain.Page;
import ru.ivanov.gaming_enjoyment.dto.GameDto;
import ru.ivanov.gaming_enjoyment.queries.GameGenrePageQuery;
import ru.ivanov.gaming_enjoyment.queries.GamePlatformPageQuery;
import ru.ivanov.gaming_enjoyment.queries.GameTitlePageQuery;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;

import java.util.List;

public interface GameService {
    GameDto getGameById(Integer id);
    Page<GameDto> getAllGames(PageQuery pageQuery);
    Page<GameDto> getGamesByTitle(GameTitlePageQuery gameTitlePageQuery);
    Page<GameDto> getGamesByGenres(GameGenrePageQuery gameGenrePageQuery);
    Page<GameDto> getGamesByPlatforms(GamePlatformPageQuery gamePlatformPageQuery);
    GameDto createGame(GameDto gameDto);
    GameDto updateGame(GameDto gameDto);
    void deleteGameById(Integer id);
}
