package com.Senla.Mathew.HotelApp.ModelsServices;

import com.Senla.Mathew.HotelApp.Models.User;
import com.Senla.Mathew.HotelApp.ModelsRepositories.RoleRepository;
import com.Senla.Mathew.HotelApp.ModelsRepositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRespository userRespository;

    @Autowired
    public UserService(UserRespository userRespository, RoleRepository roleRepository) {
        this.userRespository = userRespository;
    }

    public Optional<User> findByUsername(String username){
        return userRespository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Такого пользователя нет!")
        ));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );

    }
}
