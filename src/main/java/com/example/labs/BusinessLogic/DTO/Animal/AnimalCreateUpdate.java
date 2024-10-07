package com.example.labs.BusinessLogic.DTO.Animal;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class AnimalCreateUpdate {
    private int age;
    private float weight;

}
