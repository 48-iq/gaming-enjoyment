package ru.ivanov.gaming_enjoyment.dto;

import jakarta.persistence.Lob;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GameDto {
    private Integer id;
    private String title;
    private String description;
    private String systemRequirements;
    private List<Integer> genres; //id
    private List<Integer> platforms; //id
    @Lob
    private String image;
}
