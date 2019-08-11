package com.rcronald.ms.jsmproject.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Customer {
	private int id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "LastName cannot be null")
    private String lastName;

    @NotNull(message = "Age cannot be null")
    private int age;

    @NotNull(message = "DateOfBirth cannot be null")
    private Date dateOfBirth;

    public Customer() {

    }

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer(String name, String lastName, int age, Date dateOfBirth) {
        this.setName(name);
        this.setLastName(lastName);
        this.setAge(age);
        this.setDateOfBirth(dateOfBirth);
	}
}