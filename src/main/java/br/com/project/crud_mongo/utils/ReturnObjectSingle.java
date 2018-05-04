package br.com.project.crud_mongo.utils;

import br.com.project.crud_mongo.models.Person;
import org.springframework.hateoas.ResourceSupport;


public class ReturnObjectSingle extends ResourceSupport {

    private Person person;

    public ReturnObjectSingle(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}