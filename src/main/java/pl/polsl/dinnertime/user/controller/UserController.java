package pl.polsl.dinnertime.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.dinnertime.user.model.dto.SimpleUser;
import pl.polsl.dinnertime.user.model.dto.UserAccount;
import pl.polsl.dinnertime.user.model.entity.User;
import pl.polsl.dinnertime.user.service.OnRegistrationCompleteEvent;
import pl.polsl.dinnertime.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public UserController(UserService userService, ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping("users")
    public ResponseEntity<List<SimpleUser>> getUsersList() {
        List<SimpleUser> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("addUser")
    public void addUser(@RequestBody UserAccount userAccount, HttpServletRequest request) {
        User user = userService.addUser(userAccount);
        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(request.getRequestURL().toString(), user));
    }

    @PostMapping("/addUser/registrationConfirm")
    public void confirmRegistration(@RequestParam String token) {
        userService.activateUser(token);
    }

}
