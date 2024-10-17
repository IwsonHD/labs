package com.example.BusinessLogic.DTO;

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
