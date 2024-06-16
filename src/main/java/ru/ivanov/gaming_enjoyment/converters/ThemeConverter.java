package ru.ivanov.gaming_enjoyment.converters;

import org.springframework.stereotype.Component;
import ru.ivanov.gaming_enjoyment.dto.ThemeDto;
import ru.ivanov.gaming_enjoyment.entities.Theme;

@Component
public class ThemeConverter {
    public ThemeDto convertToDto(Theme theme) {
        return ThemeDto
                .builder()
                .id(theme.getId())
                .title(theme.getTitle())
                .description(theme.getDescription())
                .build();
    }

    public Theme convertToEntity(ThemeDto themeDto) {
        return Theme
                .builder()
                .id(themeDto.getId())
                .title(themeDto.getTitle())
                .description(themeDto.getDescription())
                .build();
    }
}
