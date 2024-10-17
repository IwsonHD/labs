package com.example.BusinessLogic.DTO;

import com.example.BusinessLogic.Enums.Continents;
import com.example.BusinessLogic.Enums.DietType;
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
