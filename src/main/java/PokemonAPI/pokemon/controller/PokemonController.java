package PokemonAPI.pokemon.controller;

import PokemonAPI.pokemon.dto.PokemonDTO;
import PokemonAPI.pokemon.entity.PokemonModel;
import PokemonAPI.pokemon.service.PokemonService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    //Add a pokemon to the database
    @PostMapping("/admin/add")
    public ResponseEntity<Void> addPokemon(@RequestBody PokemonModel pokemonModel, @NotNull UriComponentsBuilder ucb) {
        PokemonModel pokemonSaved = pokemonService.addPokemon(pokemonModel);
        URI uri = ucb.path("/get/{id}").buildAndExpand(pokemonSaved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //Get all pokemons in database
    @GetMapping("/user/all")
    public ResponseEntity<List<PokemonDTO>> getPokemons() {
        List<PokemonDTO> allPokemons = pokemonService.getPokemons();
        return ResponseEntity.ok().body(allPokemons);

    }

    //Get a Pokemon by Id
    @GetMapping("/user/get/{id}")
    public ResponseEntity<PokemonDTO> getPokemonById(@RequestParam("id") Long id) {
        ResponseEntity<PokemonDTO> response;
        PokemonDTO pokemonSelected = pokemonService.getPokemonDTO(id);
        if (pokemonSelected != null) {
            response = ResponseEntity.status(HttpStatus.OK).body(pokemonSelected);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return response;
    }

    //Update a pokemon info
    @PutMapping("/admin/update")
    public ResponseEntity<Void> updatePokemon(@RequestParam("id") Long id, @RequestBody PokemonModel updatePokemon) {
        PokemonModel actualPokemon = pokemonService.getPokemonById(id);
        if (actualPokemon != null) {
            PokemonModel updatedPokemon = new PokemonModel(
                    id,
                    updatePokemon.getSpecies(),
                    updatePokemon.getName(),
                    updatePokemon.getType(),
                    updatePokemon.getAbilities());
            pokemonService.updatePokemon(updatedPokemon);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //Delete a Pokemon
    @DeleteMapping("/admin/delete")
    public ResponseEntity<String> deletePokemon(@RequestParam("id") Long id) {
        PokemonModel selectedPokemon = pokemonService.getPokemonById(id);
        if (selectedPokemon != null) {
            pokemonService.deletePokemon(selectedPokemon);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
