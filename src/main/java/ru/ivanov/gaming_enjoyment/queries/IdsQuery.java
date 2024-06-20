package ru.ivanov.gaming_enjoyment.queries;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdsQuery {
    private List<Integer> ids; // <id>
}
