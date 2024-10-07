package com.example.labs.BusinessLogic.DTO.Animal;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class AnimalRead {
    private UUID id;
    private String specieName;
    private float weight;
    private int age;
}
