package ru.ivanov.gaming_enjoyment.services.intrf;

import org.springframework.data.domain.Page;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;
import ru.ivanov.gaming_enjoyment.dto.UserDto;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto getUserById(Integer id);

    Page<UserDto> getAllUsers(PageQuery pageQuery);

    void deleteUserById(Integer id);
}
