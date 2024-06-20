package ru.ivanov.gaming_enjoyment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthDto {
    private String username;
    private String password;
}
