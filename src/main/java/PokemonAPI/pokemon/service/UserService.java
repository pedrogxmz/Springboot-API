package PokemonAPI.pokemon.service;

import PokemonAPI.pokemon.entity.MyUsers;
import PokemonAPI.pokemon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUsers> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userObject = user.get();
            return User.builder()
                    .username(userObject.getUsername())
                    .password(userObject.getPassword())
                    .roles(getRoles(userObject))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(MyUsers user) {
        if (user.getRole() == null){
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}
