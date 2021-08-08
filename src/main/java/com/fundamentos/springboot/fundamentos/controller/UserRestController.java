package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.caseuse.GetUser;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserRestController {
    //create, get, delete, update
    private GetUser getUser;

    public UserRestController(GetUser getUser) {
        this.getUser = getUser;
    }

    List<User> get(){
        return getUser.getAll();
    }
}
