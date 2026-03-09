package com.example.taskManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskManagementSystem.model.AuthData;

@Repository
public interface authRepo extends JpaRepository<AuthData, Long>{
    
} 
