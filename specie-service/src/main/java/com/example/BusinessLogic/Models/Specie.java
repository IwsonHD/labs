package com.example.BusinessLogic.Models;

import com.example.BusinessLogic.Enums.Continents;
import com.example.BusinessLogic.Enums.DietType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "species")
public class Specie implements Serializable {
    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "occurring", nullable = false)
    private Continents occurring;

    @Enumerated(EnumType.STRING)
    @Column(name = "diet_type", nullable = false)
    private DietType dietType;
}
