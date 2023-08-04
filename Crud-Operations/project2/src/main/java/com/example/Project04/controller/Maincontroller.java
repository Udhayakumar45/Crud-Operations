package com.example.Project04.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project04.model.Tutorial;
import com.example.Project04.repository.Tutorialrepository;

@RestController
@RequestMapping("/api")
public class Maincontroller {

    @Autowired
    Tutorialrepository mainrepository;

    @GetMapping("/show_all")
    public List<Tutorial> getAllRows(){
        return (List<Tutorial>) mainrepository.findAll();

    }
    
    @PostMapping("/insert")
    public ResponseEntity<Tutorial> insertValues(@RequestBody Tutorial model){
        Tutorial _model = mainrepository.save(new Tutorial( model.getName(), model.getAge()));
        return new ResponseEntity<> (_model, HttpStatus.OK);
        
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<Tutorial> deleteAllRows(){
        mainrepository.deleteAll();
        return new ResponseEntity<Tutorial>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Tutorial> updateRow(@PathVariable("id") Long id, @RequestBody Tutorial model){
        Optional<Tutorial> _model = mainrepository.findById(id);
        if(_model.isPresent()){
            Tutorial __model = _model.get();
            __model.setName(model.getName());
            __model.setAge(model.getAge());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/show/{id}")
    public Optional<Tutorial> findbyid(@PathVariable Long id){
        return (Optional<Tutorial>) mainrepository.findById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable Long id) {
        Optional<Tutorial> ____model = mainrepository.findById(id);
        if (____model != null) {
            mainrepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}