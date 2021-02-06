package pl.polsl.dinnertime.security.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {

    @PostMapping("login")
    public void login(){}
}
