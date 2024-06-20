package ru.ivanov.gaming_enjoyment.services.intrf;

import ru.ivanov.gaming_enjoyment.dto.PlatformDto;
import ru.ivanov.gaming_enjoyment.entities.Platform;
import ru.ivanov.gaming_enjoyment.queries.IdsQuery;

import java.util.List;

public interface PlatformService {

    List<PlatformDto> getAllPlatforms();

    PlatformDto getPlatformById(Integer id);

    PlatformDto createPlatform(PlatformDto platformDto);

    PlatformDto updatePlatform(PlatformDto platformDto);

    List<PlatformDto> getPlatformsByIds(IdsQuery idsQuery);

    void deletePlatform(Integer id);
}
