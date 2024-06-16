package ru.ivanov.gaming_enjoyment.converters;

import org.springframework.stereotype.Component;
import ru.ivanov.gaming_enjoyment.dto.PlatformDto;
import ru.ivanov.gaming_enjoyment.entities.Game;
import ru.ivanov.gaming_enjoyment.entities.Platform;

import java.util.ArrayList;

@Component
public class PlatformConverter {
    public PlatformDto convertToDto(Platform platform) {
        return PlatformDto.builder()
                .id(platform.getId())
                .title(platform.getTitle())
                .description(platform.getDescription())
                .build();
    }

    public Platform convertToEntity(PlatformDto platformDto) {

        return Platform.builder()
                .id(platformDto.getId())
                .title(platformDto.getTitle())
                .description(platformDto.getDescription())
                .build();
    }
}
