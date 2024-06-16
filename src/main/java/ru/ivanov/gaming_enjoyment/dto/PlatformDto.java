package ru.ivanov.gaming_enjoyment.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PlatformDto {
    private Integer id;
    private String title;
    private String description;
}
