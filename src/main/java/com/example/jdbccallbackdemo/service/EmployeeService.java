package com.example.jdbccallbackdemo.service;

import com.example.jdbccallbackdemo.dao.EmployeeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;

    public void doAction(){
        System.out.println("employee count::"+
                employeeDao.countEmployee());
        System.out.println("Find AllEmployee:");
        System.out.println("");
    }
}
