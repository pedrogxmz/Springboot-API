package PokemonAPI.pokemon.repository;

import PokemonAPI.pokemon.entity.MyUsers;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUsers, Long> {


    Optional<MyUsers> findByUsername(@NotNull String username);
}
