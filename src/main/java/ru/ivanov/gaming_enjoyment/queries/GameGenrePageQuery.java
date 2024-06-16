package ru.ivanov.gaming_enjoyment.queries;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GameGenrePageQuery {
    private List<Integer> genres;
    private Integer page;
    private Integer size;
}
