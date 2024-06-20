package ru.ivanov.gaming_enjoyment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.gaming_enjoyment.dto.GameDto;
import ru.ivanov.gaming_enjoyment.queries.*;
import ru.ivanov.gaming_enjoyment.services.intrf.GameService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/games")
@RequiredArgsConstructor()
public class GameController {

    private final GameService gameService;

    @GetMapping("/user/playing")
    public ResponseEntity<Page<GameDto>> getGamesUserPlaying(@RequestBody GameUserPageQuery gameUserPageQuery) {
        return ResponseEntity.ok(gameService.getGamesUserPlaying(gameUserPageQuery));
    }

    @GetMapping("/user/played")
    public ResponseEntity<Page<GameDto>> getGamesUserPlayed(@RequestBody GameUserPageQuery gameUserPageQuery) {
        return ResponseEntity.ok(gameService.getGamesUserPlayed(gameUserPageQuery));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<GameDto>> getAllGames(@RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size) {
        PageQuery query = PageQuery.builder().page(page).size(size).build();
        return ResponseEntity.ok(gameService.getAllGames(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Integer id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @GetMapping("/title")
    public ResponseEntity<Page<GameDto>> getGameByTitle(@RequestParam("title") String title,
                                                        @RequestParam("page") Integer page,
                                                        @RequestParam("size") Integer size) {
        GameTitlePageQuery query = GameTitlePageQuery.builder().page(page).size(size).title(title).build();
        return ResponseEntity.ok(gameService.getGamesByTitle(query));
    }

    @GetMapping("/platform")
    public ResponseEntity<Page<GameDto>> getGamesByPlatform(@RequestParam("platform") List<Integer> platforms,
                                                            @RequestParam("page") Integer page,
                                                            @RequestParam("size") Integer size) {
        GamePlatformPageQuery query = GamePlatformPageQuery.builder().page(page).size(size).platforms(platforms).build();
        return ResponseEntity.ok(gameService.getGamesByPlatforms(query));
    }

    @GetMapping("/genre")
    public ResponseEntity<Page<GameDto>> getGamesByGenre(@RequestParam("platform") List<Integer> genres,
                                                         @RequestParam("page") Integer page,
                                                         @RequestParam("size") Integer size) {
        GameGenrePageQuery query = GameGenrePageQuery.builder().page(page).size(size).genres(genres).build();
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
