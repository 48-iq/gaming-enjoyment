package ru.ivanov.gaming_enjoyment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.converters.ThemeConverter;
import ru.ivanov.gaming_enjoyment.dto.ThemeDto;
import ru.ivanov.gaming_enjoyment.entities.Theme;
import ru.ivanov.gaming_enjoyment.exceptions.EntityNotFoundException;
import ru.ivanov.gaming_enjoyment.exceptions.NullIdException;
import ru.ivanov.gaming_enjoyment.repositories.GroupRepository;
import ru.ivanov.gaming_enjoyment.repositories.ThemeRepository;
import ru.ivanov.gaming_enjoyment.repositories.UserRepository;
import ru.ivanov.gaming_enjoyment.services.intrf.ThemeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ThemeConverter themeConverter;
    @Override
    public ThemeDto getThemeById(Integer id) {
        Theme theme = themeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Theme with id " + id + " not found"));
        return themeConverter.convertToDto(theme);
    }

    @Override
    public List<ThemeDto> getAllThemes() {
        return themeRepository.findAll()
                .stream().map(themeConverter::convertToDto)
                .toList();
    }

    @Override
    public ThemeDto createTheme(ThemeDto themeDto) {
        Theme theme = themeConverter.convertToEntity(themeDto);
        return themeConverter.convertToDto(themeRepository.save(theme));
    }

    @Override
    public ThemeDto updateTheme(ThemeDto themeDto) {
        if (themeDto.getId() == null)
            throw new NullIdException("Id must not be null when updating theme");
        Theme theme = themeRepository.findById(themeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Theme with id " + themeDto.getId() + " not found"));
        if (themeDto.getDescription() != null) {
            theme.setDescription(themeDto.getDescription());
        }
        if (themeDto.getTitle() != null) {
            theme.setTitle(themeDto.getTitle());
        }
        return themeConverter.convertToDto(themeRepository.save(theme));
    }

    @Override
    public void deleteTheme(Integer id) {
        themeRepository.deleteById(id);
    }
}
