package ru.ivanov.gaming_enjoyment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ThemeDto {
    private Integer id;
    private String title;
    private String description;
}
