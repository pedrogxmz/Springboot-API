package PokemonAPI.pokemon.repository;

import PokemonAPI.pokemon.entity.PokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonModel,Long> {
}
