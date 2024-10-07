package com.example.labs.BusinessLogic.DTO.Specie;

import com.example.labs.BusinessLogic.Enums.Continents;
import com.example.labs.BusinessLogic.Enums.DietType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class SpecieCollectionRead {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Specie{
        private UUID id;
        private String name;
        private DietType dietType;
        private Continents occurring;
    }
    private List<Specie> species;
}
