package com.kefas.ActivityTracker.controllers;

import com.kefas.Weekeighttask.dto.UsersDto;
import com.kefas.Weekeighttask.exception.UsersAlreadyExistException;
import com.kefas.Weekeighttask.exception.UsersNotFoundException;
import com.kefas.Weekeighttask.service.serviceImpl.UsersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UsersController {

    private final UsersServiceImpl usersService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UsersDto usersDto) throws UsersAlreadyExistException {
        return new ResponseEntity<>(usersService.register(usersDto), HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsersDto usersDto) throws UsersNotFoundException {
        return new ResponseEntity<>(usersService.login(usersDto), HttpStatus.ACCEPTED);
    }

}
