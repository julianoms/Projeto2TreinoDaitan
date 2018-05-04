package br.com.project.crud_mongo.services;

import br.com.project.crud_mongo.daos.PersonRepository;
import br.com.project.crud_mongo.models.Person;
import br.com.project.crud_mongo.utils.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;


    public Person getPersonById(String id) throws PersonNotFoundException {

        Person obj = personRepository.findBy_id(id);
        if(obj == null){
            throw new PersonNotFoundException(id);
        }

        return obj;
    }

    public List<Person> getPeople(){
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(list::add);
        return list;
    }

    public void createPerson(Person person){
        personRepository.save(person);
    }

    public void updatePerson(Person person){
        personRepository.save(person);
    }

    public void deletePerson(String id) throws PersonNotFoundException {

        Person person = personRepository.findBy_id(id);
        if(person == null){
            throw new PersonNotFoundException(id);
        }
        personRepository.delete(person);
    }

    public List<Person> getPersonByName(String name){
        return personRepository.findByName(name);
    }

    public List<Person> getPersonByCountry(String country){
        return personRepository.findByCountry(country);
    }

}
