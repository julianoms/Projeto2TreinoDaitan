package br.com.project.crud_mongo.services;

import br.com.project.crud_mongo.daos.PersonRepository;
import br.com.project.crud_mongo.models.Person;
import br.com.project.crud_mongo.utils.personNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;


    public Person GetPersonById(String id) throws personNotFoundException {

        Person obj = personRepository.findBy_id(id);
        if(obj == null){
            throw new personNotFoundException(id);
        }

        return obj;
    }
    public List<Person> GetPeople(){
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(list::add);
        return list;
    }
    public void CreatePerson(Person person){
        personRepository.save(person);
    }

    public void updatePerson(Person person){
        personRepository.save(person);
    }
    public void deletePerson(String id) throws personNotFoundException {

        Person person = personRepository.findBy_id(id);
        if(person == null){
            throw new personNotFoundException(id);
        }
        personRepository.delete(person);
    }
    public List<Person> getPersonByName(String name){
        return personRepository.findByName(name);
    }

}
