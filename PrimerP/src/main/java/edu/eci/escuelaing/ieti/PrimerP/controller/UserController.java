package edu.eci.escuelaing.ieti.PrimerP.controller;

import edu.eci.escuelaing.ieti.PrimerP.data.User;
import edu.eci.escuelaing.ieti.PrimerP.dto.UserDto;
import edu.eci.escuelaing.ieti.PrimerP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( "/v1/user" )
public class UserController
{
    private final UserService userService;

    public UserController(@Autowired UserService userService )
    {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> all()
    {
        return new ResponseEntity<List<User>>(userService.all(), HttpStatus.OK);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<User> findById( @PathVariable String id )
    {
        User userMatch=userService.findById(id);
        if(userMatch==null){
            return new ResponseEntity<User> (HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<User>(userMatch, HttpStatus.FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<User> create( @RequestBody UserDto userDto )
    {
        User newUser=new User(UUID.randomUUID().toString(), userDto.getName(), userDto.getEmail(), userDto.getLastName());
        userService.create(newUser);
        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<User> update( @RequestBody UserDto userDto, @PathVariable String id )
    {
        User userToPut=new User(id, userDto.getName(), userDto.getEmail(), userDto.getLastName());
        userService.update(userToPut, id);
        return new ResponseEntity<User>(userToPut, HttpStatus.OK);
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id )
    {
        if(userService.findById(id)==null){
            return new ResponseEntity<Boolean> (HttpStatus.NOT_FOUND);
        }else{
            userService.deleteById(id);
            return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
        }
    }
}