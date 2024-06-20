package ru.ivanov.gaming_enjoyment.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;
import ru.ivanov.gaming_enjoyment.dto.UserDto;
import ru.ivanov.gaming_enjoyment.services.intrf.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto registeredUser = userService.registerUser(userDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size) {
        PageQuery pageQuery = PageQuery.builder().page(page).size(size).build();
        Page<UserDto> usersDtoPage = userService.getAllUsers(pageQuery);

        return ResponseEntity.ok(usersDtoPage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser() {
        UserDto userDto = userService.getCurrentUser();
        return ResponseEntity.ok(userDto);
    }
}
