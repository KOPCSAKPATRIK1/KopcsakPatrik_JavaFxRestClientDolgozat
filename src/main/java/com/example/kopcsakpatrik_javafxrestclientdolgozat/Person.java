package com.example.kopcsakpatrik_javafxrestclientdolgozat;

import com.google.gson.annotations.Expose;

public class Person {
    private int id;
    @Expose
    private String name;
    @Expose
    private int salary;
    @Expose
    private int cvv;

    public Person(int id, String name, int email, int age) {
        this.id = id;
        this.name = name;
        this.salary = email;
        this.cvv = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}