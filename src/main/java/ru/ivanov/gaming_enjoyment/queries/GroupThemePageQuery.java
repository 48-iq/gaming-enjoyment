package ru.ivanov.gaming_enjoyment.queries;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GroupThemePageQuery {
    private List<Integer> themes; //ids
    private Integer page;
    private Integer size;
}
