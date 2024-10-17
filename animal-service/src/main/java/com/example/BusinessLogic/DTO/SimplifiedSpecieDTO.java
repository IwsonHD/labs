package com.example.BusinessLogic.DTO;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class SimplifiedSpecieDTO {
    private UUID id;
    private String name;
}
