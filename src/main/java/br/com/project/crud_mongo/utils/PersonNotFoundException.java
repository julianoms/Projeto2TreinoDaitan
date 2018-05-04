package br.com.project.crud_mongo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Person Not Found") //404
public class PersonNotFoundException extends  Exception{

        public PersonNotFoundException(String id){
            super("PersonNotFoundException with id="+id);
        }
}

