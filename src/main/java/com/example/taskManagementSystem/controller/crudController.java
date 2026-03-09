package com.example.taskManagementSystem.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.taskManagementSystem.JwtToken.jwtToken;
import com.example.taskManagementSystem.dto.loginRequestDto;
import com.example.taskManagementSystem.model.AuthData;
import com.example.taskManagementSystem.service.crudService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class crudController {
    @Autowired
    private jwtToken jToken;

    crudService ser;
    crudController(crudService ser){
        this.ser = ser;
    }

    @PostMapping("/register")
    public AuthData postMethodName(@RequestBody AuthData data) {
        //TODO: process POST request
        
        return ser.post(data);
    }

    @GetMapping("/login")
    public List<AuthData> getMethodName() {
        return ser.Getall();
    }

    @GetMapping("/login/{id}")
    public Optional<AuthData> getIdMethodName(@PathVariable Long id) {
        return ser.GetById(id);
    }
    
    @GetMapping("/loginCheck/{id}")
    public String getMethodName(@PathVariable Long id, @RequestBody loginRequestDto iRdto) {
        return ser.checkLogin(id, iRdto);
    }
    
    @PutMapping("/update/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody AuthData User) {
        return ser.UpdateAll(id, User);
    }

    @DeleteMapping("/delete")
    public String delete(){
        return ser.DeleteAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        return ser.deleteById(id);
    }

    // token 

    @PostMapping("/post-token-check")
    public String postCheckToken(@RequestBody String email){
        // String webToken = jToken.generateToken(email);
        return jToken.generateToken(email);
    }
    
    
}
