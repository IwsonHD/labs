package com.example.labs.BusinessLogic.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "specie")
@EqualsAndHashCode()
@Entity
@Table(name = "animals")
public class Animal implements Serializable {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specie_id", nullable = false)
    private Specie specie;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "weight", nullable = false)
    private float weight;
}
