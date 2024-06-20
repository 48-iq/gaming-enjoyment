package ru.ivanov.gaming_enjoyment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.gaming_enjoyment.dto.GenreDto;
import ru.ivanov.gaming_enjoyment.queries.IdsQuery;
import ru.ivanov.gaming_enjoyment.services.intrf.GenreService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/all")
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }


    @GetMapping("/ids")
    public ResponseEntity<List<GenreDto>> getGenresByIds(@RequestParam("ids") List<Integer> ids) {
        IdsQuery idsQuery = IdsQuery.builder().ids(ids).build();
        return ResponseEntity.ok(genreService.getGenresByIds(idsQuery));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Integer id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto genreDto) {
        return ResponseEntity.ok(genreService.createGenre(genreDto));
    }

    @PutMapping("/update")
    public ResponseEntity<GenreDto> updateGenre(@RequestBody GenreDto genreDto) {
        return ResponseEntity.ok(genreService.updateGenre(genreDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id) {
        genreService.deleteGenre(id);
        return ResponseEntity.ok().build();
    }


}
