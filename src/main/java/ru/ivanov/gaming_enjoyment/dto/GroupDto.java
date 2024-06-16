package ru.ivanov.gaming_enjoyment.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GroupDto {

    private Integer id;
    private String title;
    private String description;
    private byte[] image;
    private List<Integer> users;
    private List<Integer> games;
    private List<Integer> themes;
}
