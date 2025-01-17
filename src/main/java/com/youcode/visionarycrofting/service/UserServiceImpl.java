package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.entity.Role;
import com.youcode.visionarycrofting.entity.User;
import com.youcode.visionarycrofting.repository.RoleRepository;
import com.youcode.visionarycrofting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException {
        User user = userRepository.findByUsername ( username );
        if (user == null ) {
            log.error ( "User not found" );
            throw  new UsernameNotFoundException ( "User not found" );
        } else {
            log.info ( "User found in database : {} ", username );
        }
        Collection< SimpleGrantedAuthority > authorities = new ArrayList <> (  );
        user.getRoles ().forEach ( role -> {
            authorities.add ( new SimpleGrantedAuthority ( role.getName () ) );
        });
        return new org.springframework.security.core.userdetails.User (user.getUsername (), user.getPassword (), authorities  );
    }

    @Override
    public User saveUSer ( User user ) {
        log.info ("saving new user {} to the database", user.getName ());
        user.setPassword ( passwordEncoder.encode ( user.getPassword () ) );
        return userRepository.save(user);
    }

    @Override
    public Role saveRole ( Role role ) {
        log.info ("saving new role {} to the database", role.getName ());
        return roleRepository.save ( role );
    }

    @Override
    public void addRoleToUSer ( String username , String roleName ) {
        log.info ("dding role {} to user {}", username, roleName);
        User user = userRepository.findByUsername ( username );
        Role role = roleRepository.findByName ( roleName );

        user.getRoles ().add ( role );
    }

    @Override
    public User getUser ( String username ) {
        log.info ("Fetching user {} ", username);
        return userRepository.findByUsername ( username );
    }

    @Override
    public List < User > getUSers ( ) {
        log.info ("Fetching all users");
        return userRepository.findAll ();
    }

}
