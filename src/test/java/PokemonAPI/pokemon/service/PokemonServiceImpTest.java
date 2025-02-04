package PokemonAPI.pokemon.service;

import PokemonAPI.pokemon.entity.PokemonModel;
import PokemonAPI.pokemon.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImpTest {

    private final PokemonModel PokeExample1 = new PokemonModel(1L,"Lizard","Charmander","fire","Blaze");
    private final PokemonModel PokeExample2 = new PokemonModel(2L,"Superpower","Machop","Fighting","Guts");

    private final List<PokemonModel> mockDatabase = Arrays.asList(this.PokeExample1,this.PokeExample2);

    @Mock
    PokemonRepository pokemonRepository;

    @InjectMocks
    PokemonServiceImp pokemonServiceImp;

    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(PokemonServiceImpTest.class);
    }

    @Test
    public void addingPokemonFlow() {

        PokemonModel pokemonToAdd = this.mockDatabase.get(new Random().nextInt(this.mockDatabase.size()));

        Mockito.when(this.pokemonRepository.save(Mockito.any(PokemonModel.class))).thenReturn(pokemonToAdd);

        this.pokemonServiceImp.addPokemon(pokemonToAdd);

        Mockito.verify(this.pokemonRepository,Mockito.times(1))
                .save(Mockito.any(PokemonModel.class));

    }

    @Test
    public void deletePokemonFlow(){

        Mockito.doNothing().when(this.pokemonRepository).delete(Mockito.any(PokemonModel.class));

        this.pokemonServiceImp.deletePokemon(this.mockDatabase.get(new Random().nextInt(this.mockDatabase.size())));

        Mockito.verify(this.pokemonRepository,Mockito.times(1)).delete(Mockito.any(PokemonModel.class));

    }


}