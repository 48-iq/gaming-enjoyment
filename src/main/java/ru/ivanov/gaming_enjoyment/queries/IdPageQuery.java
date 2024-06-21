package ru.ivanov.gaming_enjoyment.queries;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdPageQuery {

    private Integer id;
    private Integer page;
    private Integer size;
}
