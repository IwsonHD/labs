package com.example.BusinessLogic.Models;



import com.example.BusinessLogic.Models.Animal;
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
public class SimplifiedSpecie implements Serializable {
    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;


    @OneToMany(mappedBy = "specie", fetch = FetchType.EAGER)
    private List<Animal> animals;
}
