package com.contoso.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contoso.user.User;
import com.contoso.user.UserServiceV1;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/v1")
public class UserControllerV1 {

    private UserServiceV1 userService;

    @Autowired
    public UserControllerV1(UserServiceV1 userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/me")
    public ResponseEntity<?> me(Principal principal,HttpServletRequest request) {
    	// /me === http://172.17.0.10:8181/uaa/v1/me  or http://localhost:8181/uaa/v1/me
    	System.out.println("/me === "+request.getRequestURL().toString());
        User user = null;
        if(principal != null) {
            user = userService.getUserByUsername(principal.getName());
        }

        return Optional.ofNullable(user)
                .map(a -> new ResponseEntity<User>(a, HttpStatus.OK))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
