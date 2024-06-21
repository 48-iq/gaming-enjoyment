package ru.ivanov.gaming_enjoyment.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatDto {
    private Integer id;
    private String title;
    private List<Integer> users; //ids
}
