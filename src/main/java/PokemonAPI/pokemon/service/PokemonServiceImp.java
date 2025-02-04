package PokemonAPI.pokemon.service;

import PokemonAPI.pokemon.dto.PokemonDTO;
import PokemonAPI.pokemon.dto.PokemonDTOMapper;
import PokemonAPI.pokemon.entity.PokemonModel;
import PokemonAPI.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PokemonServiceImp implements PokemonService {

    private PokemonDTOMapper pokemonDTOMapper;
    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public PokemonModel addPokemon(PokemonModel pokemonModel) {
        return pokemonRepository.save(pokemonModel);
    }

    @Override
    public List<PokemonDTO> getPokemons() {
        return pokemonRepository
                .findAll()
                .stream()
                .map(pokemonDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public PokemonModel getPokemonById(Long Id) {
        PokemonModel requestedPokemon;
       Optional<PokemonModel> pokemonOptional = pokemonRepository.findById(Id);
        if (pokemonOptional.isPresent()) {
            requestedPokemon = pokemonOptional.get();
            return requestedPokemon;
        }
        return null;
    }

    @Override
    public PokemonDTO getPokemonDTO(Long Id) {
        PokemonDTO requestedPokemon;
        Optional<PokemonDTO> pokemonOptional = pokemonRepository.findById(Id).map(pokemonDTOMapper);
        if (pokemonOptional.isPresent()) {
            requestedPokemon = pokemonOptional.get();
            return requestedPokemon;
        }
        return null;
    }

    @Override
    public void updatePokemon(PokemonModel updatedPokemon) {
        pokemonRepository.save(updatedPokemon);
    }

    @Override
    public void deletePokemon(PokemonModel selectedPokemon) {
        pokemonRepository.delete(selectedPokemon);
    }
}
