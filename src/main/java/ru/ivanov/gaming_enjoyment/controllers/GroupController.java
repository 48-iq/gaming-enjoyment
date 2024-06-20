package ru.ivanov.gaming_enjoyment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.gaming_enjoyment.dto.GroupDto;
import ru.ivanov.gaming_enjoyment.queries.GroupThemePageQuery;
import ru.ivanov.gaming_enjoyment.queries.GroupTitlePageQuery;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;
import ru.ivanov.gaming_enjoyment.services.intrf.GroupService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(groupService.getGroupById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<GroupDto>> getAllGroups(@RequestParam("page") Integer page,
                                                       @RequestParam("size") Integer size) {
        PageQuery query = PageQuery.builder().page(page).size(size).build();
        return ResponseEntity.ok().body(groupService.getAllGroups(query));
    }

    @GetMapping("/title")
    public ResponseEntity<Page<GroupDto>> getGroupsByTitle(@RequestParam("title") String title,
                                                           @RequestParam("page") Integer page,
                                                           @RequestParam("size") Integer size) {
        GroupTitlePageQuery query = GroupTitlePageQuery.builder().page(page).size(size).title(title).build();
        return ResponseEntity.ok().body(groupService.getGroupsByTitle(query));
    }

    @GetMapping("/theme")
    public ResponseEntity<Page<GroupDto>> getGroupsByThemes(@RequestParam("theme") List<Integer> themes,
                                                            @RequestParam("page") Integer page,
                                                            @RequestParam("size") Integer size) {
        GroupThemePageQuery query = GroupThemePageQuery.builder().page(page).size(size).themes(themes).build();
        return ResponseEntity.ok().body(groupService.getGroupsByThemes(query));
    }

    @PostMapping("/create")
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        return ResponseEntity.ok().body(groupService.createGroup(groupDto));
    }

    @PutMapping("/update")
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
        return ResponseEntity.ok().body(groupService.updateGroup(groupDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}
