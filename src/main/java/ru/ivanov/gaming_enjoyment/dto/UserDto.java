package ru.ivanov.gaming_enjoyment.dto;

import lombok.*;
import ru.ivanov.gaming_enjoyment.enums.Role;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String status;
    private String email;
    private String role;
    private List<Integer> gamesPlayed; //id>
    private List<Integer> gamesPlaying; //id>
    private List<Integer> groups; //id
    private List<Integer> themes; //id
    private byte[] image;
}
