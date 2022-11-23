package se.sigma.sigmatechbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sigma.sigmatechbackend.exception.DataNotFoundException;
import se.sigma.sigmatechbackend.models.dto.UserDto;
import se.sigma.sigmatechbackend.models.entity.User;
import se.sigma.sigmatechbackend.repository.UserRepository;
import se.sigma.sigmatechbackend.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto findById(int userId) throws DataNotFoundException {
        if (userId < 0) throw new IllegalArgumentException("userId must be 0 or more");
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("user not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findByEmail(String email) throws DataNotFoundException {
        if (email == null || email.equals("")) throw new IllegalArgumentException("email was null or empty");
        User user = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("user not found"));
        return modelMapper.map(user, UserDto.class);
    }


    @Override
    public List<UserDto> findAll() {
        return modelMapper.map(userRepository.findAll(),
                new TypeToken<List<UserDto>>() {
                }.getType());
    }

    @Override
    public UserDto create(UserDto userDto) {
        if (userDto == null) throw new IllegalArgumentException("UserDto was null");
        User userToSave = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(userToSave);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto) throws DataNotFoundException {
        if (userDto == null) throw new IllegalArgumentException("UserDto was null");
        if (!userRepository.existsById(userDto.getUserId()))
            throw new DataNotFoundException("User with email:" + userDto.getEmail() + " can not be found");
        User userToBeSaved = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(userToBeSaved);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public boolean delete(int id) throws DataNotFoundException {
        if (id < 0) throw new IllegalArgumentException("id must be 0 or more");
        if (!userRepository.existsById(id))
            throw new DataNotFoundException("User that id was not be found");
        userRepository.deleteById(id);
        return true;
    }
}
