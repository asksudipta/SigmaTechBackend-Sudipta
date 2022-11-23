package se.sigma.sigmatechbackend.service;


import se.sigma.sigmatechbackend.exception.DataNotFoundException;
import se.sigma.sigmatechbackend.models.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findById(int userId) throws DataNotFoundException;

    UserDto findByEmail(String email) throws DataNotFoundException;

    List<UserDto> findAll();

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto) throws DataNotFoundException;

    boolean delete(int userId) throws DataNotFoundException;

}
