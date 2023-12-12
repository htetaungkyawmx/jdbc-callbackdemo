package com.example.jdbccallbackdemo.ds;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Date;
@AllArgsConstructor
@ToString
public class Employee {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private Date hiredDate;
    private double salary;


}
