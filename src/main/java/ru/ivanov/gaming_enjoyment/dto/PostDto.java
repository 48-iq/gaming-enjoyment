package ru.ivanov.gaming_enjoyment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostDto {

    private Integer id;
    private Integer user;
    private Integer group;
    private String title;
    private String image;
    private String text;
}
