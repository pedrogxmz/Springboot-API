package PokemonAPI.pokemon.controller;

import PokemonAPI.pokemon.entity.MyUsers;
import PokemonAPI.pokemon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public ResponseEntity<Void> createUser(@RequestBody MyUsers user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/get-users")
    public ResponseEntity<List<MyUsers>> getAllUsers() {
        List<MyUsers> users = userRepository.findAll();
        return ResponseEntity.ok(users);

    }
}
