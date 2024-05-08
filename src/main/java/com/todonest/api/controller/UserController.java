package com.todonest.api.controller;

import com.todonest.api.constants.UserConstants;
import com.todonest.api.dto.ResponseDto;
import com.todonest.api.entity.User;
import com.todonest.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user",produces = {"application/json"})
@CrossOrigin(origins = "*") // Allow requests from any origin
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createTodo(@RequestBody User user) {
        iUserService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_201,UserConstants.MESSAGE_201));
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        User user = iUserService.getUserByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
}
