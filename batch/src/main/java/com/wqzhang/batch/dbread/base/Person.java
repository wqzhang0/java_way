package com.dramakill.batch.dbread.base;


public class Person {
    private Long person_id;
    private String last_name;
    private String first_name;

    public Person() {
    }

    public Person(Long person_id, String last_name, String first_name) {
        this.person_id = person_id;
        this.last_name = last_name;
        this.first_name = first_name;
    }


    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public String toString() {
        return "firstName: " + first_name + ", lastName: " + last_name;
    }

}
