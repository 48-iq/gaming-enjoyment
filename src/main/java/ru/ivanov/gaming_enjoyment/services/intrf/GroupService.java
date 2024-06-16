package ru.ivanov.gaming_enjoyment.services.intrf;

import org.springframework.data.domain.Page;
import ru.ivanov.gaming_enjoyment.dto.GroupDto;
import ru.ivanov.gaming_enjoyment.queries.GroupThemePageQuery;
import ru.ivanov.gaming_enjoyment.queries.GroupTitlePageQuery;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;

public interface GroupService {
    GroupDto getGroupById(Integer id);
    Page<GroupDto> getAllGroups(PageQuery query);
    Page<GroupDto> getGroupsByTitle(GroupTitlePageQuery query);
    Page<GroupDto> getGroupsByThemes(GroupThemePageQuery query);
    GroupDto createGroup(GroupDto groupDto);
    GroupDto updateGroup(GroupDto groupDto);
    void deleteGroup(Integer id);
}
