package ru.ivanov.gaming_enjoyment.queries;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostPageQuery {
    private Integer userId;
    private Integer page;
    private Integer size;
}
