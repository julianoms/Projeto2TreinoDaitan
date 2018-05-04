package br.com.project.crud_mongo.daos;

import br.com.project.crud_mongo.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person,Long> {

    Person findBy_id(String id);
    List<Person> findByName(String name);
    List<Person> findByCountry(String country);

}
