package ru.ivanov.gaming_enjoyment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MessageDto {
    private Integer id;
    private String content;
    private Integer authorId;
    private Integer chatId;
    private String time;
}
