package com.contoso.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
	    
	@RequestMapping("/user")
	public Principal user(Principal user, HttpServletRequest request) {
		// /user === http://192.168.99.100:8181/uaa/user or http://localhost:8181/uaa/user
		System.out.println("/user === "+request.getRequestURL().toString());
		return user;
	}
	
}
