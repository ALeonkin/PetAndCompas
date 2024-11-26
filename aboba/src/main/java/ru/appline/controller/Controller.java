package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();

    private static final AtomicInteger newID = new AtomicInteger(1);

    private static final String stringCreate = "Питомец успешно создан";
    private static final String stringDelete = "Питомец успешно удален";
    private static final String stringUpdate = "Питомец успешно изменен";

    @PostMapping(value = "/createPet", consumes = "application/json")
    public String createPet(@RequestBody Pet pet){
        petModel.add(pet, newID.getAndIncrement());

        return stringCreate;
    }
    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer,Pet> getAll(){
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet",consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String,Integer> id){
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/deletePet", consumes = "application/json")
    public String deletePet(@RequestBody Map<String,Integer> id){
        petModel.delete(id.get("id"));

        return stringDelete;
    }

    @PutMapping(value = "/updatePet", consumes = "application/json")
    public String updatePet(@RequestBody Map<String,Pet> request){
        int id = Integer.parseInt(request.keySet().iterator().next());
        Pet pet = request.get(String.valueOf(id));
        petModel.update(pet,id);

        return stringUpdate;
    }
}
