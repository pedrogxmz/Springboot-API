package PokemonAPI.pokemon.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "pokemon")
public class PokemonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String species;
    private String name;
    private String type;
    private String abilities;

}
