package com.example.labs.BusinessLogic.DTO.Animal;

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
public class AnimalCollectionRead {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Animal{
        private UUID id;
        private String specieName;
        private float weight;
        private float age;
    }

    @Singular
    private List<Animal> animals;
}
