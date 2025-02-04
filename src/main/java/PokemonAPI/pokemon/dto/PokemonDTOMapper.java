package PokemonAPI.pokemon.dto;

import PokemonAPI.pokemon.entity.PokemonModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class PokemonDTOMapper implements Function<PokemonModel,PokemonDTO> {
    @Override
    public PokemonDTO apply(PokemonModel pokemonModel) {
        return new PokemonDTO(
                pokemonModel.getSpecies(),
                pokemonModel.getName(),
                pokemonModel.getType(),
                pokemonModel.getAbilities()
        );
    }
}
