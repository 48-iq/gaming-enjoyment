package ru.ivanov.gaming_enjoyment.queries;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GamePlatformPageQuery {
    private List<Integer> platforms;
    private Integer page;
    private Integer size;

}
