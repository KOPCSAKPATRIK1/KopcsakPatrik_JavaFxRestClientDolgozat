package com.example.kopcsakpatrik_javafxrestclientdolgozat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee {
    private int id;
    @Expose
    @SerializedName(value = "Name")
    private String name;
    @Expose
    @SerializedName(value = "Salary")
    private int salary;
    @Expose
    @SerializedName(value = "CVV")
    private int cvv;

    public Employee(int id, String name, int email, int cvv) {
        this.id = id;
        this.name = name;
        this.salary = email;
        this.cvv = cvv;
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