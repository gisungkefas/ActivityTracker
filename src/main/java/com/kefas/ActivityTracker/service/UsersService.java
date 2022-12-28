package com.kefas.ActivityTracker.service;

import com.kefas.ActivityTracker.dto.UsersDto;
import com.kefas.ActivityTracker.exception.UsersAlreadyExistException;
import com.kefas.ActivityTracker.exception.UsersNotFoundException;

public interface UsersService {

    String register(UsersDto usersDto) throws UsersAlreadyExistException;

    String login(UsersDto usersDto) throws UsersNotFoundException;

}
