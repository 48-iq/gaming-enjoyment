package ru.ivanov.gaming_enjoyment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.converters.PlatformConverter;
import ru.ivanov.gaming_enjoyment.dto.PlatformDto;
import ru.ivanov.gaming_enjoyment.entities.Platform;
import ru.ivanov.gaming_enjoyment.exceptions.EntityNotFoundException;
import ru.ivanov.gaming_enjoyment.exceptions.NullIdException;
import ru.ivanov.gaming_enjoyment.repositories.GameRepository;
import ru.ivanov.gaming_enjoyment.repositories.PlatformRepository;
import ru.ivanov.gaming_enjoyment.services.intrf.PlatformService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {
    private final PlatformRepository platformRepository;
    private final PlatformConverter platformConverter;
    private final GameRepository gameRepository;
    @Override
    public List<PlatformDto> getAllPlatforms() {
        return platformRepository.findAll().stream()
                .map(platformConverter::convertToDto)
                .toList();
    }

    @Override
    public PlatformDto getPlatformById(Integer id) {
        Platform platform = platformRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Platform with id " + id + " not found")
                );
        return platformConverter.convertToDto(platform);
    }

    @Override
    public PlatformDto createPlatform(PlatformDto platformDto) {
        Platform platform = platformConverter.convertToEntity(platformDto);
        return platformConverter.convertToDto(platformRepository.save(platform));
    }

    @Override
    public PlatformDto updatePlatform(PlatformDto platformDto) {
        if (platformDto.getId() == null)
            throw new NullIdException("Id must not be null when updating platform");

        Platform platform = platformRepository.findById(platformDto.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Platform with id " + platformDto.getId() + " not found")
                );
        if (platformDto.getTitle() != null) {
            platform.setTitle(platformDto.getTitle());
        }
        if (platformDto.getDescription() != null) {
            platform.setDescription(platformDto.getDescription());
        }
        return platformConverter.convertToDto(platformRepository.save(platform));
    }

    @Override
    public void deletePlatform(Integer id) {
        platformRepository.deleteById(id);
    }
}
