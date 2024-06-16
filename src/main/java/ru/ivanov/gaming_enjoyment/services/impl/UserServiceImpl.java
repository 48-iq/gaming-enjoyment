package ru.ivanov.gaming_enjoyment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.converters.UserConverter;
import ru.ivanov.gaming_enjoyment.enums.Role;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;
import ru.ivanov.gaming_enjoyment.dto.UserDto;
import ru.ivanov.gaming_enjoyment.entities.User;
import ru.ivanov.gaming_enjoyment.exceptions.EntityNotFoundException;
import ru.ivanov.gaming_enjoyment.exceptions.NotNullIdException;
import ru.ivanov.gaming_enjoyment.repositories.GameRepository;
import ru.ivanov.gaming_enjoyment.repositories.GroupRepository;
import ru.ivanov.gaming_enjoyment.repositories.ThemeRepository;
import ru.ivanov.gaming_enjoyment.repositories.UserRepository;
import ru.ivanov.gaming_enjoyment.services.intrf.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GroupRepository groupRepository;
    private final ThemeRepository themeRepository;

    public UserDto registerUser(UserDto userDto) {
        if (userDto.getId() != null) {
            throw new NotNullIdException("Id must be null when registering new user");
        }
        User user = userConverter.convertToEntity(userDto);
        user.setGamesPlayed(
                gameRepository.findAllByIds(userDto.getGamesPlayed())
        );
        user.setGamesPlaying(
                gameRepository.findAllByIds(userDto.getGamesPlaying())
        );
        user = userRepository.save(user);
        return userConverter.convertToDto(user);
    }

    public UserDto updateUser(UserDto userDto) {
        if (userDto.getId() == null) {
            throw new NotNullIdException("Id must not be null when updating user");
        }
        User user = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new EntityNotFoundException("User with id " + userDto.getId() + " not found")
        );
        if (userDto.getGamesPlayed() != null) {
            user.setGamesPlayed(
                    gameRepository.findAllByIds(userDto.getGamesPlayed())
            );
        }
        if (userDto.getGamesPlaying() != null) {
            user.setGamesPlaying(
                    gameRepository.findAllByIds(userDto.getGamesPlaying())
            );
        }
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getGroups() != null) {
            user.setGroups(
                    groupRepository.findAllByIds(userDto.getGroups())
            );
        }
        if (userDto.getImage() != null) {
            user.setImage(userDto.getImage());
        }
        if (userDto.getRole() != null) {
            user.setRole(Role.valueOf(userDto.getRole()));
        }
        if (userDto.getThemes() != null) {
            user.setThemes(
                    themeRepository.findAllByIds(userDto.getThemes())
            );
        }
        if (userDto.getStatus() != null) {
            user.setStatus(userDto.getStatus());
        }
        user = userRepository.save(user);
        return userConverter.convertToDto(user);
    }

    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );
        return userConverter.convertToDto(user);
    }

    public Page<UserDto> getAllUsers(PageQuery pageQuery) {
        Page<User> usersPage = userRepository
                .findAll(org.springframework.data.domain.PageRequest.of(pageQuery.getPage(), pageQuery.getSize()));
        return new PageImpl<UserDto>(usersPage.getContent()
                .stream().map(userConverter::convertToDto).toList(),
                usersPage.getPageable(), usersPage.getTotalElements());
    }

    public void deleteUserById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
