package com.example.BusinessLogic.DTO;

import com.example.BusinessLogic.Enums.Continents;
import com.example.BusinessLogic.Enums.DietType;
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
