package com.example.labs.BusinessLogic.DTO.Specie;

import com.example.labs.BusinessLogic.Enums.Continents;
import com.example.labs.BusinessLogic.Enums.DietType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class SpecieCreateUpdate {
    private String name;
    private DietType dietType;
    private Continents occurring;
}
