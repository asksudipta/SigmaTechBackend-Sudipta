package se.sigma.sigmatechbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sigma.sigmatechbackend.exception.DataNotFoundException;
import se.sigma.sigmatechbackend.models.dto.UserDto;
import se.sigma.sigmatechbackend.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable ("userId") int userId) throws DataNotFoundException {
        UserDto userDto = userService.findById(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable ("email") String email) throws DataNotFoundException {
        UserDto userDto = userService.findByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public  ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> userDtoList = userService.findAll();
        return ResponseEntity.ok(userDtoList);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto)
    {
        System.out.println("userDto = " + userDto);
        UserDto createdUserDto = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto userDto) throws DataNotFoundException
    {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.update(userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable("userId") int userId) throws DataNotFoundException {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
