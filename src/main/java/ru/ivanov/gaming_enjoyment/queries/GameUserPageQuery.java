package ru.ivanov.gaming_enjoyment.queries;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GameUserPageQuery {
    private Integer userId;
    private Integer page;
    private Integer size;
}
