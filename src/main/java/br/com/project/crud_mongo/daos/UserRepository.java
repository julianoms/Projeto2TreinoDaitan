package br.com.project.crud_mongo.daos;

import br.com.project.crud_mongo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    void deleteByUsername(String username);

}