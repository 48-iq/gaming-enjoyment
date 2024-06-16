package ru.ivanov.gaming_enjoyment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.gaming_enjoyment.dto.GameDto;
import ru.ivanov.gaming_enjoyment.queries.GameGenrePageQuery;
import ru.ivanov.gaming_enjoyment.queries.GamePlatformPageQuery;
import ru.ivanov.gaming_enjoyment.queries.GameTitlePageQuery;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;
import ru.ivanov.gaming_enjoyment.services.intrf.GameService;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor()
public class GameController {

    private final GameService gameService;

    @GetMapping("/all")
    public ResponseEntity<Page<GameDto>> getAllGames(@RequestBody PageQuery query) {
        return ResponseEntity.ok(gameService.getAllGames(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Integer id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @GetMapping("/title")
    public ResponseEntity<Page<GameDto>> getGameByTitle(@RequestBody GameTitlePageQuery query) {
        return ResponseEntity.ok(gameService.getGamesByTitle(query));
    }

    @GetMapping("/platform")
    public ResponseEntity<Page<GameDto>> getGamesByPlatform(@RequestBody GamePlatformPageQuery query) {
        return ResponseEntity.ok(gameService.getGamesByPlatforms(query));
    }

    @GetMapping("/genre")
    public ResponseEntity<Page<GameDto>> getGamesByGenre(@RequestBody GameGenrePageQuery query) {
        return ResponseEntity.ok(gameService.getGamesByGenres(query));
    }

    @PostMapping("/create")
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {
        return ResponseEntity.ok(gameService.createGame(gameDto));
    }

    @PutMapping("/update")
    public ResponseEntity<GameDto> updateGame(@RequestBody GameDto gameDto) {
        return ResponseEntity.ok(gameService.updateGame(gameDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Integer id) {
        gameService.deleteGameById(id);
        return ResponseEntity.ok().build();
    }

}
