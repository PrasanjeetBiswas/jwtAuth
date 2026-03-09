package com.example.taskManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.taskManagementSystem.dto.loginRequestDto;
import com.example.taskManagementSystem.model.AuthData;
import com.example.taskManagementSystem.repository.authRepo;
import com.example.taskManagementSystem.ssecureConfig.secureConfig;

@Service
public class crudService {
authRepo repo;
PasswordEncoder pEncoder;
crudService(authRepo repo,PasswordEncoder pEncoder){
    this.repo = repo;
    this.pEncoder = pEncoder;
}

public AuthData post(AuthData data){
    String password = data.getPassword();
    String encodedPassword = pEncoder.encode(password);
    data.setPassword(encodedPassword);
    repo.save(data);
    return data;
}

public List<AuthData> Getall(){
   return repo.findAll();
}

public Optional<AuthData>GetById(Long id){
   return repo.findById(id);
}

    public String checkLogin(Long id,loginRequestDto lRdto){
        Optional<AuthData> loginDb = repo.findById(id);
        
        if(loginDb.isPresent()){
            if(loginDb.get().getEmail().equals(lRdto.getEmail())){
                if(loginDb.get().getPassword().equals(lRdto.getPassword())){
                    return "logged in";
                }
            }
        }
        return "invalid user";
    }



    public String DeleteAll() {
        repo.deleteAll();
        return "Deleted All";
    }

    public String deleteById(Long id) {
        repo.deleteById(id);
        return "User Has Been Deleted";
    }

    public String UpdateAll(Long id, AuthData User_Data) {
        AuthData db_Data = repo.findById(id).orElseThrow();
        if(User_Data.getName() != null){
            db_Data.setName(User_Data.getName());
        }
        if(User_Data.getEmail() != null){
            db_Data.setEmail(User_Data.getEmail());
        }
        if(User_Data.getPassword() != null){
            db_Data.setPassword(User_Data.getPassword());
        }

        repo.save(db_Data);
        return "Updated";
    }







    //spring security

    // password encoder
    
    
}
