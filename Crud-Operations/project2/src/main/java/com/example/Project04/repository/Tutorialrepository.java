package com.example.Project04.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project04.model.Tutorial;

public interface Tutorialrepository extends JpaRepository<Tutorial,Long>{
    
}