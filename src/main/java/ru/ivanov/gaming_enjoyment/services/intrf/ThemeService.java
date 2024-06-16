package ru.ivanov.gaming_enjoyment.services.intrf;

import ru.ivanov.gaming_enjoyment.dto.ThemeDto;

import java.util.List;

public interface ThemeService {
    ThemeDto getThemeById(Integer id);
    List<ThemeDto> getAllThemes();
    ThemeDto createTheme(ThemeDto themeDto);
    ThemeDto updateTheme(ThemeDto themeDto);
    void deleteTheme(Integer id);
}
