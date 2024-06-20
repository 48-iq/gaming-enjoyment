package ru.ivanov.gaming_enjoyment.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.converters.UserConverter;
import ru.ivanov.gaming_enjoyment.enums.Role;
import ru.ivanov.gaming_enjoyment.exceptions.*;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;
import ru.ivanov.gaming_enjoyment.dto.UserDto;
import ru.ivanov.gaming_enjoyment.entities.User;
import ru.ivanov.gaming_enjoyment.repositories.GameRepository;
import ru.ivanov.gaming_enjoyment.repositories.GroupRepository;
import ru.ivanov.gaming_enjoyment.repositories.ThemeRepository;
import ru.ivanov.gaming_enjoyment.repositories.UserRepository;
import ru.ivanov.gaming_enjoyment.security.UserDetailsImpl;
import ru.ivanov.gaming_enjoyment.services.intrf.UserService;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Value("${register.admin.password:admin}")
    private String adminPassword;
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GroupRepository groupRepository;
    private final ThemeRepository themeRepository;
    private final PasswordEncoder passwordEncoder;

    public Integer currentUserId() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        return user.getId();
    }

    @Override
    @Transactional
    public UserDto getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        User curUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + user.getId() + " not found"));
        return userConverter.convertToDto(curUser);
    }
    @Transactional
    public UserDto registerUser(UserDto userDto) {
        if (userDto.getPassword().length() < 8) {
            throw new RegisterIncorrectDataException("Password must be at least 8 characters long");
        }
        if (userDto.getUsername().length() < 3) {
            throw new RegisterIncorrectDataException("Username must be at least 3 characters long");
        }
        if (userDto.getRole().equals("ADMIN") && !userDto.getAdminPassword().equals(adminPassword)) {
            System.out.println(userDto.getAdminPassword());
            System.out.println(adminPassword);
            throw new RegisterIncorrectDataException("Wrong admin password");
        }
        if (userDto.getId() != null) {
            throw new NotNullIdException("Id must be null when registering new user");
        }
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new EntityAlreadyExistException("User with username " + userDto.getUsername() + " already exists");
        }
        User user = userConverter.convertToEntity(userDto);
        if (userDto.getGamesPlayed() != null)
            user.setGamesPlayed(
                gameRepository.findAllByIds(userDto.getGamesPlayed())
            );
        if (userDto.getGamesPlaying() != null)
            user.setGamesPlaying(
                gameRepository.findAllByIds(userDto.getGamesPlaying())
            );
        if (userDto.getGroups() != null)
            user.setGroups(
                groupRepository.findAllByIds(userDto.getGroups())
            );
        if (userDto.getThemes() != null)
            user.setThemes(
                themeRepository.findAllByIds(userDto.getThemes())
            );
        if (userDto.getFriends() != null)
            user.setFriends(
                userRepository.findAllByIds(userDto.getFriends())
            );
        if (userDto.getCreatedGroups() != null)
            user.setCreatedGroups(
                groupRepository.findAllByIds(userDto.getCreatedGroups())
            );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return userConverter.convertToDto(user);
    }
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        if (userDto.getId() == null) {
            throw new NotNullIdException("Id must not be null when updating user");
        }
        if (!userDto.getId().equals(currentUserId())) {
            throw new NotAllowedActionException("You can update only your profile");
        }
        User user = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new EntityNotFoundException("User with id " + userDto.getId() + " not found")
        );
        if (user.getRole() != Role.ADMIN && userDto.getRole().equals("ADMIN") && !userDto.getAdminPassword().equals(adminPassword)) {
            System.out.println(userDto.getAdminPassword());
            System.out.println(adminPassword);
            throw new NotAllowedActionException("Wrong admin password");
        }
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
        if (userDto.getPassword() != null &&  userDto.getPassword().length() >= 8) {
            user.setPassword(userDto.getPassword());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        if (userDto.getFriends() != null) {
            user.setFriends(
                    userRepository.findAllByIds(userDto.getFriends())
            );
        }
        user = userRepository.save(user);
        return userConverter.convertToDto(user);
    }
    @Transactional
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );
        return userConverter.convertToDto(user);
    }
    @Transactional
    public Page<UserDto> getAllUsers(PageQuery pageQuery) {
        Page<User> usersPage = userRepository
                .findAll(org.springframework.data.domain.PageRequest.of(pageQuery.getPage(), pageQuery.getSize()));
        return new PageImpl<UserDto>(usersPage.getContent()
                .stream().map(userConverter::convertToDto).toList(),
                usersPage.getPageable(), usersPage.getTotalElements());
    }
    @Transactional
    public void deleteUserById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
    @Transactional
    @Override
    public UserDto findUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("User with username " + username + " not found")
        );
        return userConverter.convertToDto(user);
    }
}
