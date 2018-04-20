package br.com.project.crud_mongo.controllers;

import br.com.project.crud_mongo.models.Person;
import br.com.project.crud_mongo.services.PersonService;
import br.com.project.crud_mongo.utils.PersonNotFoundExeption;
import br.com.project.crud_mongo.utils.ReturnObject;
import br.com.project.crud_mongo.utils.ReturnObjectList;
import br.com.project.crud_mongo.utils.ReturnObjectSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/create")
    public ResponseEntity<ReturnObject> create(@RequestBody Person person) throws PersonNotFoundExeption {

        personService.CreatePerson(person);

        ReturnObject object = new ReturnObject("created","Person created");
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).body(object);
    }

    @GetMapping("/read")
    public ResponseEntity<ReturnObjectList> read(){
        List<Person> people = personService.GetPeople();
        ReturnObjectList object = new ReturnObjectList("Ok","List of all included people", people);
        object.add(linkTo(PersonController.class).withSelfRel());
        return ResponseEntity.ok(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnObject> update (
            @PathVariable(value = "id")long id, @RequestBody Person person) throws PersonNotFoundExeption {

        person.setId(personService.GetPersonById(id).getId());
        personService.updatePerson(person);

        ReturnObject object = new ReturnObject("Ok","Person Updated");
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        return  ResponseEntity.ok(object);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReturnObject> delete (@PathVariable(value = "id") long id)  {

        personService.deletePerson(id);
        ReturnObject object = new ReturnObject("deleted","Person Deleted");
        return ResponseEntity.ok(object);
    }

    @RequestMapping("/readByName")
    public ResponseEntity<ReturnObjectList> readByName(@RequestBody String name){
        List<Person> people = personService.getPersonByName(name);
        ReturnObjectList object = new ReturnObjectList("Ok","List of people named :"+name,people);
        object.add(linkTo(PersonController.class).withSelfRel());
        return ResponseEntity.ok(object);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnObjectSingle> readById(@PathVariable(value = "id") Long id) throws PersonNotFoundExeption {

        Person person = personService.GetPersonById(id);



        ReturnObjectSingle object = new ReturnObjectSingle("Ok","Person retrieved",person);
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        return ResponseEntity.ok(object);
    }

}
