package ru.ivanov.gaming_enjoyment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.converters.GroupConverter;
import ru.ivanov.gaming_enjoyment.dto.GroupDto;
import ru.ivanov.gaming_enjoyment.entities.Group;
import ru.ivanov.gaming_enjoyment.exceptions.EntityNotFoundException;
import ru.ivanov.gaming_enjoyment.exceptions.NullIdException;
import ru.ivanov.gaming_enjoyment.queries.GroupThemePageQuery;
import ru.ivanov.gaming_enjoyment.queries.GroupTitlePageQuery;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;
import ru.ivanov.gaming_enjoyment.repositories.GameRepository;
import ru.ivanov.gaming_enjoyment.repositories.GroupRepository;
import ru.ivanov.gaming_enjoyment.repositories.ThemeRepository;
import ru.ivanov.gaming_enjoyment.repositories.UserRepository;
import ru.ivanov.gaming_enjoyment.services.intrf.GroupService;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final ThemeRepository themeRepository;
    private final GroupConverter groupConverter;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    @Override
    public GroupDto getGroupById(Integer id) {
        return groupConverter.convertToDto(
                groupRepository.findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("Group with id " + id + " not found")
                        )
        );
    }

    @Override
    public Page<GroupDto> getAllGroups(PageQuery query) {
        Page<Group> groupPage = groupRepository.findAll(PageRequest.of(query.getPage(), query.getSize()));

        return new PageImpl<GroupDto>(
                groupPage.getContent().stream()
                        .map(groupConverter::convertToDto)
                        .toList(),
                groupPage.getPageable(),
                groupPage.getTotalElements()
        );
    }

    @Override
    public Page<GroupDto> getGroupsByTitle(GroupTitlePageQuery query) {
        Page<Group> groupPage = groupRepository.findAllByTitleLike(query.getTitle(),
                PageRequest.of(query.getPage(), query.getSize()));

        return new PageImpl<GroupDto>(
                groupPage.getContent().stream()
                        .map(groupConverter::convertToDto)
                        .toList(),
                groupPage.getPageable(),
                groupPage.getTotalElements()
        );
    }

    @Override
    public Page<GroupDto> getGroupsByThemes(GroupThemePageQuery query) {
        Page<Group> groupPage = groupRepository.findAllByThemeIds(query.getThemes(),
                query.getSize(),
                PageRequest.of(query.getPage(), query.getSize()));
        return new PageImpl<GroupDto>(groupPage.getContent().stream().map(groupConverter::convertToDto).toList(),
                groupPage.getPageable(),
                groupPage.getTotalElements());
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Group group = groupConverter.convertToEntity(groupDto);
        if (groupDto.getThemes() != null) {
            group.setThemes(
                    themeRepository.findAllByIds(groupDto.getThemes())
            );
        }
        if (groupDto.getCreator() != null) {
            group.setCreator(
                    userRepository.findById(groupDto.getCreator())
                            .orElseThrow(
                                    () -> new EntityNotFoundException("User with id " + groupDto.getCreator() + " not found")
                            )
            );
        }
        if (groupDto.getUsers() != null) {
            group.setUsers(
                    userRepository.findAllByIds(groupDto.getUsers())
            );
        }
        if(groupDto.getGames() != null) {
            group.setGames(
                    gameRepository.findAllByIds(groupDto.getGames())
            );
        }
        return groupConverter.convertToDto(groupRepository.save(group));
    }

    @Override
    public GroupDto updateGroup(GroupDto groupDto) {
        System.out.println(groupDto);
        if (groupDto.getId() == null) {
            throw new NullIdException("Group id cannot be null");
        }
        Group group = groupRepository.findById(groupDto.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Group with id " + groupDto.getId() + " not found")
                );
        if (groupDto.getTitle() != null) {
            group.setTitle(groupDto.getTitle());

        }
        if (groupDto.getImage() != null) {
            group.setImage(groupDto.getImage());
        }
        if (groupDto.getGames() != null) {
            group.setGames(
                    gameRepository.findAllByIds(groupDto.getGames())
            );
        }
        if (groupDto.getDescription() != null) {
            group.setDescription(groupDto.getDescription());
        }
        if (groupDto.getThemes() != null) {
            group.setThemes(
                    themeRepository.findAllByIds(groupDto.getThemes())
            );
        }
        if (groupDto.getUsers() != null) {
            group.setUsers(
                    userRepository.findAllByIds(groupDto.getUsers())
            );
        }
        return groupConverter.convertToDto(groupRepository.save(group));
    }

    @Override
    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }
}
