package br.com.project.crud_mongo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Person Not Found") //404
public class personNotFoundException extends  Exception{

        public personNotFoundException(String id){
            super("PersonNotFoundException with id="+id);
        }
}

