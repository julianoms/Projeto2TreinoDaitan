package br.com.project.crud_mongo.controllers;

import br.com.project.crud_mongo.models.Person;
import br.com.project.crud_mongo.services.PersonService;
import br.com.project.crud_mongo.utils.PersonNotFoundException;
import br.com.project.crud_mongo.utils.ReturnObject;
import br.com.project.crud_mongo.utils.ReturnObjectSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<ReturnObject> create(@RequestBody Person person) throws PersonNotFoundException {

        personService.createPerson(person);

        List<ReturnObjectSingle> returnList = responseSingleCreator(person);
        ReturnObject returnObj = new ReturnObject("Created","Person Created",returnList);


        return ResponseEntity.status(201).body(returnObj);
    }

    @GetMapping
    public ResponseEntity<ReturnObject> read(@Param(value = "name")String name,
                                             @Param (value = "country")String country) throws PersonNotFoundException {

        List<ReturnObjectSingle> returnList = new ArrayList<>();

        if(name != null && country != null){
            List<Person> people = personService.getPersonByNameAndCountry(name,country);
            responseListCreator(people, returnList);
            ReturnObject returnObj = new ReturnObject("Ok","List of people named :"+name + " from :"+country,returnList);
            return ResponseEntity.ok(returnObj);
        }
        if(name != null) {
            List<Person> people = personService.getPersonByName(name);
            responseListCreator(people, returnList);
            ReturnObject returnObj = new ReturnObject("Ok","List of people named :"+name,returnList);
            return ResponseEntity.ok(returnObj);
        }
        if(country != null){
            List<Person> people = personService.getPersonByCountry(country);
            responseListCreator(people, returnList);
            ReturnObject returnObj = new ReturnObject("Ok","List of people from: "+country,returnList);
            return ResponseEntity.ok(returnObj);
        }

        List<Person> people = personService.getPeople();

        responseListCreator(people, returnList);
        ReturnObject returnObj = new ReturnObject("Ok","List of all people:",returnList);
        return ResponseEntity.ok(returnObj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnObject> update (@PathVariable(value = "id")String id,
                                                @RequestBody Person person) throws PersonNotFoundException {

        person.setId(personService.getPersonById(id).getId());
        personService.updatePerson(person);

        List<ReturnObjectSingle> returnList = responseSingleCreator(person);
        ReturnObject returnObj = new ReturnObject("Ok","Person Updated",returnList);

        return  ResponseEntity.ok(returnObj);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReturnObject> delete (@PathVariable(value = "id") String id) throws PersonNotFoundException {

        personService.deletePerson(id);
        ReturnObject object = new ReturnObject("deleted","Person Deleted");
        return ResponseEntity.ok(object);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnObject> readById(@PathVariable(value = "id") String id) throws PersonNotFoundException {

        Person person = personService.getPersonById(id);

        List<ReturnObjectSingle> returnList = responseSingleCreator(person);
        ReturnObject returnObj = new ReturnObject("Ok","Person retrieved",returnList);
        return  ResponseEntity.ok(returnObj);
    }

    private List<ReturnObjectSingle> responseSingleCreator(Person person) throws PersonNotFoundException {
        List<ReturnObjectSingle> returnList = new ArrayList<>();
        ReturnObjectSingle obj = new ReturnObjectSingle(person);
        obj.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        obj.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        returnList.add(obj);
        return returnList;
    }

    private void responseListCreator(List<Person> people, List<ReturnObjectSingle> returnList) throws PersonNotFoundException {
        ReturnObjectSingle object;
        for(Person person:people){
            object = new ReturnObjectSingle(person);
            object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
            object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
            returnList.add(object);
        }
    }
}
