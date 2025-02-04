package PokemonAPI.pokemon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name="my_users")
public class MyUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;
    String role;
}
