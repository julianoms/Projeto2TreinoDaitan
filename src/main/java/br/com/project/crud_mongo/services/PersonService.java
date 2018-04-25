package br.com.project.crud_mongo.services;

import br.com.project.crud_mongo.daos.PersonRepository;
import br.com.project.crud_mongo.models.Person;
import br.com.project.crud_mongo.utils.PersonNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;


    public Person GetPersonById(String id) throws PersonNotFoundExeption {

        Person obj = personRepository.findBy_id(id);
        if(obj == null){
            throw new PersonNotFoundExeption(id);
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
    public void deletePerson(String id){
        Person person = personRepository.findBy_id(id);
        personRepository.delete(person);
    }
    public List<Person> getPersonByName(String name){
        return personRepository.findByName(name);
    }

}
