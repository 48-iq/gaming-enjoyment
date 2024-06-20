package ru.ivanov.gaming_enjoyment.services.intrf;


import org.springframework.data.domain.Page;
import ru.ivanov.gaming_enjoyment.dto.GameDto;
import ru.ivanov.gaming_enjoyment.queries.*;

import java.util.List;

public interface GameService {
    GameDto getGameById(Integer id);
    Page<GameDto> getAllGames(PageQuery pageQuery);
    Page<GameDto> getGamesByTitle(GameTitlePageQuery gameTitlePageQuery);
    Page<GameDto> getGamesByGenres(GameGenrePageQuery gameGenrePageQuery);
    Page<GameDto> getGamesByPlatforms(GamePlatformPageQuery gamePlatformPageQuery);
    Page<GameDto> getGamesUserPlayed(GameUserPageQuery gameUserPageQuery);
    Page<GameDto> getGamesUserPlaying(GameUserPageQuery gameUserPageQuery);
    GameDto createGame(GameDto gameDto);
    GameDto updateGame(GameDto gameDto);
    void deleteGameById(Integer id);
}
