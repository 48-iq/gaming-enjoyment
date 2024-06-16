package ru.ivanov.gaming_enjoyment.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GenreDto {
    private Integer id;
    private String title;
    private String description;
}
