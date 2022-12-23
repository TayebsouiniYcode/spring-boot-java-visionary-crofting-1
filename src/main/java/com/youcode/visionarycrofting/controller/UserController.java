package com.youcode.visionarycrofting.controller;

import com.youcode.visionarycrofting.classes.RoleToUserForm;
import com.youcode.visionarycrofting.domain.Role;
import com.youcode.visionarycrofting.domain.User;
import com.youcode.visionarycrofting.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity< List< User > > getUsers() {
        return ResponseEntity.ok ().body ( userService.getUSers () );
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser( @RequestBody User user ) {
        URI uri = URI.create ( ServletUriComponentsBuilder.fromCurrentContextPath ().path ( "/user/save" ).toUriString () );
        return ResponseEntity.created (uri).body ( userService.saveUSer (user) );
    }

    @PostMapping("/role/save")
    public ResponseEntity< Role > saveRole( @RequestBody Role role ) {
        URI uri = URI.create ( ServletUriComponentsBuilder.fromCurrentContextPath ().path ( "/user/role/save" ).toUriString () );
        return ResponseEntity.created (uri).body ( userService.saveRole (role) );
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser( @RequestBody RoleToUserForm form ) {
        userService.addRoleToUSer ( form.getUsername ( ) , form.getRoleName ( ) );
        return ResponseEntity.ok ().build ( );
    }

}
