package br.com.project.crud_mongo.controllers;

import br.com.project.crud_mongo.models.Person;
import br.com.project.crud_mongo.services.PersonService;
import br.com.project.crud_mongo.utils.personNotFoundException;
import br.com.project.crud_mongo.utils.ReturnObject;
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
    public ResponseEntity<ReturnObject> create(@RequestBody Person person) throws personNotFoundException {

        personService.CreatePerson(person);

        ReturnObject object = new ReturnObject("created","Person created");
            object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
            object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).body(object);
    }

    @GetMapping("/read")
    public ResponseEntity<ReturnObject> read(){
        List<Person> people = personService.GetPeople();
        ReturnObject object = new ReturnObject("Ok","List of all included people", people);
        object.add(linkTo(PersonController.class).withSelfRel());
        return ResponseEntity.ok(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnObject> update (
            @PathVariable(value = "id")String id, @RequestBody Person person) throws personNotFoundException {

        person.setId(personService.GetPersonById(id).getId());
        personService.updatePerson(person);

        ReturnObject object = new ReturnObject("Ok","Person Updated");
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        return  ResponseEntity.ok(object);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReturnObject> delete (@PathVariable(value = "id") String id) throws personNotFoundException {

        personService.deletePerson(id);
        ReturnObject object = new ReturnObject("deleted","Person Deleted");
        return ResponseEntity.ok(object);
    }

    @RequestMapping("/readByName")
    public ResponseEntity<ReturnObject> readByName(@RequestBody Person person){
        List<Person> people = personService.getPersonByName(person.getName());
        ReturnObject object = new ReturnObject("Ok","List of people named :"+person.getName(),people);
        object.add(linkTo(PersonController.class).withSelfRel());
        return ResponseEntity.ok(object);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnObject> readById(@PathVariable(value = "id") String id) throws personNotFoundException {

        Person person = personService.GetPersonById(id);

        ReturnObject object = new ReturnObject("Ok","Person retrieved",person);
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        return ResponseEntity.ok(object);
    }

}
