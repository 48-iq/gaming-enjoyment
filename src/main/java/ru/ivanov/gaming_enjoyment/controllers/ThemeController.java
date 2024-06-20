package ru.ivanov.gaming_enjoyment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.gaming_enjoyment.dto.ThemeDto;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;
import ru.ivanov.gaming_enjoyment.services.intrf.ThemeService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/themes")
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDto> getThemeById(@PathVariable Integer id) {
        return ResponseEntity.ok(themeService.getThemeById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ThemeDto>> getAllThemes() {
        return ResponseEntity.ok(themeService.getAllThemes());
    }

    @PostMapping("/create")
    public ResponseEntity<ThemeDto> createTheme(@RequestBody ThemeDto themeDto) {
        return ResponseEntity.ok(themeService.createTheme(themeDto));
    }

    @PutMapping("/update")
    public ResponseEntity<ThemeDto> updateTheme(@RequestBody ThemeDto themeDto) {
        return ResponseEntity.ok(themeService.updateTheme(themeDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTheme(@PathVariable Integer id) {
        themeService.deleteTheme(id);
        return ResponseEntity.ok().build();
    }

}
