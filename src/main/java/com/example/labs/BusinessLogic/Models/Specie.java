package com.example.labs.BusinessLogic.Models;

import com.example.labs.Enums.Continents;
import com.example.labs.Enums.DietType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "animals")
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "specie", fetch = FetchType.LAZY)
    private List<Animal> animals;
}
