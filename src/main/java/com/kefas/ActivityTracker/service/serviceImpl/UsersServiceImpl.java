package com.kefas.ActivityTracker.service.serviceImpl;

import com.kefas.ActivityTracker.dto.UsersDto;
import com.kefas.ActivityTracker.entity.Users;
import com.kefas.ActivityTracker.exception.UsersAlreadyExistException;
import com.kefas.ActivityTracker.exception.UsersNotFoundException;
import com.kefas.ActivityTracker.repository.TaskRepository;
import com.kefas.ActivityTracker.repository.UsersRepository;
import com.kefas.ActivityTracker.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final TaskRepository taskRepository;

    @Override
    public String register(UsersDto usersDto) throws UsersAlreadyExistException {
        Users users = new Users();
        boolean existUser = usersRepository.findUsersByEmail(usersDto.getEmail()).isPresent();
        if(existUser){
            throw new UsersAlreadyExistException("User with "+usersDto.getEmail()+"Already Exist");
        }
        users.setFirstname(usersDto.getFirstname());
        users.setLastname(usersDto.getLastname());
        users.setEmail(usersDto.getEmail());
        users.setPhoneNumber(usersDto.getPhoneNumber());
        users.setPassword(usersDto.getPassword());
        usersRepository.save(users);
        return "Registration Successfully";
    }

    @Override
    public String login(UsersDto usersDto) throws UsersNotFoundException {
        Users users = usersRepository.findByEmailAndPassword(usersDto.getEmail(), usersDto.getPassword());
        if(users == null){
            throw new UsersNotFoundException("Fill out the required fields");
        }
        if(!Objects.equals(users.getPassword(), usersDto.getPassword()) || !Objects.equals(users.getEmail(), usersDto.getEmail())){
            return "Invalid Email or Password";
        }
        return "Login Successfully";
    }
}
