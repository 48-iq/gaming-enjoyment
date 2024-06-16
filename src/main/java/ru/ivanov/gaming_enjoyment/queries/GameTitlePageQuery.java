package ru.ivanov.gaming_enjoyment.queries;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GameTitlePageQuery {

    private String title;
    private Integer page;
    private Integer size;
}
