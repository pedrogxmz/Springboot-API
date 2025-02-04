package PokemonAPI.pokemon.service;

import PokemonAPI.pokemon.dto.PokemonDTO;
import PokemonAPI.pokemon.entity.PokemonModel;

import java.util.List;



public interface PokemonService {

    PokemonModel addPokemon(PokemonModel pokemonModel);
    List<PokemonDTO> getPokemons();
    PokemonModel getPokemonById(Long Id);
    PokemonDTO getPokemonDTO(Long Id);
    void updatePokemon(PokemonModel updatePokemon);
    void deletePokemon(PokemonModel selectedPokemon);
}
