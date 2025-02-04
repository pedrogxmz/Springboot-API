package PokemonAPI.pokemon.controller;

import PokemonAPI.pokemon.dto.PokemonDTO;
import PokemonAPI.pokemon.entity.PokemonModel;
import PokemonAPI.pokemon.service.PokemonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PokemonControllerTest {

    //Create test database
    public PokemonModel PokeExample1 = new PokemonModel(1L,"Lizard","Charmander","Fire","Blaze");
    public PokemonModel PokeExample2 = new PokemonModel(1L,"Superpower","Machop","Fighting","Guts");

    public List<PokemonModel> mockDatabase = Arrays.asList(this.PokeExample1,this.PokeExample2);

    @Mock
    PokemonService pokemonService;

    @InjectMocks
    PokemonController pokemonController;

    @BeforeAll
    static void setUp() {
        MockitoAnnotations.openMocks(PokemonControllerTest.class);
    }

    @Test
    public void addPokemonTest(){

        PokemonModel pokemonToAdd = this.mockDatabase.get(new Random().nextInt(this.mockDatabase.size()));

        UriComponentsBuilder ucb = UriComponentsBuilder.newInstance();

        Mockito.when(this.pokemonService.addPokemon(Mockito.any(PokemonModel.class))).thenReturn(pokemonToAdd);

        ResponseEntity<Void> response = this.pokemonController.addPokemon(pokemonToAdd,ucb);
        Mockito.verify(this.pokemonService,Mockito.times(1))
                .addPokemon(Mockito.any(PokemonModel.class));

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    public void getPokemonCorrectTest(){

        PokemonDTO pokemonDTO1= new PokemonDTO("Monster","Pikachu","Lighting","Shockwave");
        Mockito.when(this.pokemonService.getPokemonDTO(Mockito.any(Long.class))).thenReturn(pokemonDTO1);

        ResponseEntity<PokemonDTO> pokemonSelected = this.pokemonController.getPokemonById(1L);

        assertNotNull(pokemonSelected.getBody());
        assertEquals(HttpStatus.OK,pokemonSelected.getStatusCode());
        assertEquals("Shockwave",pokemonSelected.getBody().abilities());

    }

    @Test
    public void getPokemonFailTest(){

            Mockito.when(this.pokemonService.getPokemonDTO(Mockito.any(Long.class))).thenReturn(null);

        ResponseEntity<PokemonDTO> pokemonSelected = this.pokemonController.getPokemonById(10L);

        assertEquals(HttpStatus.NOT_FOUND,pokemonSelected.getStatusCode());
        assertNull(pokemonSelected.getBody());

    }

    @Test
    public void getPokemonsTest(){
        PokemonDTO pokemonDTO1= new PokemonDTO("Monster","Pikachu","Lighting","Shockwave");
        PokemonDTO pokemonDTO2= new PokemonDTO("Monster","Pikachu2","Lighting","Shockwave");
         List<PokemonDTO> DTOList = Arrays.asList(pokemonDTO1,pokemonDTO2);

        Mockito.when(this.pokemonService.getPokemons()).thenReturn(DTOList);

        ResponseEntity<List<PokemonDTO>> allPokemons = this.pokemonController.getPokemons();

        assertEquals(HttpStatus.OK,allPokemons.getStatusCode());
        assertNotNull(allPokemons.getBody());

    }



    @Test
    public void updatePokemonTestCorrect(){
       PokemonModel updatedPokemon = this.mockDatabase.get(1);

       Mockito.when(this.pokemonService.getPokemonById(Mockito.any(Long.class))).thenReturn(updatedPokemon);
       Mockito.doNothing().when(this.pokemonService).updatePokemon(Mockito.any(PokemonModel.class));

        ResponseEntity<Void> updateOperation = this.pokemonController.updatePokemon(1L,updatedPokemon);

        assertEquals(HttpStatus.NO_CONTENT,updateOperation.getStatusCode());
        assertNull(updateOperation.getBody());
    }

    @Test
    public void updatePokemonTestFail(){
        PokemonModel updatedPokemon = this.mockDatabase.get(1);
        Mockito.when(this.pokemonService.getPokemonById(Mockito.any(Long.class))).thenReturn(null);

        ResponseEntity<Void> updateOperation = this.pokemonController.updatePokemon(10L,updatedPokemon);

        assertEquals(HttpStatus.NOT_FOUND,updateOperation.getStatusCode());
        assertNull(updateOperation.getBody());
    }
}