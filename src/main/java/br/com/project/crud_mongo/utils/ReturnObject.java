package br.com.project.crud_mongo.utils;

import java.util.List;

public class ReturnObject {

    private String status;
    private String message;
    private List<ReturnObjectSingle> people;

    public ReturnObject(){}

    public ReturnObject(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ReturnObject(String status, String message, List<ReturnObjectSingle> people) {
        this.status = status;
        this.message = message;
        this.people = people;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ReturnObjectSingle> getPeople() {
        return people;
    }

    public void setPeople(List<ReturnObjectSingle> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "ReturnObj{" +
                "status='" + this.status + '\'' +
                ", message='" + this.message + '\'' +
                ", people=" + this.people +
                '}';
    }
}
