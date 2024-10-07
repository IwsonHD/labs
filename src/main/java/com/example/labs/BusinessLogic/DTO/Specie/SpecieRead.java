package com.example.labs.BusinessLogic.DTO.Specie;

import com.example.labs.BusinessLogic.Enums.Continents;
import com.example.labs.BusinessLogic.Enums.DietType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class SpecieRead {
    private UUID id;
    private String name;
    private Continents occurring;
    private DietType dietType;
}
