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
        employeeDao.findAllEmployee()
                        .forEach(System.out::println);
        System.out.println("Average with RowCallBackHandler::");
        System.out.println(employeeDao.averageWithRowCallBack());
        System.out.println("Average with ResultSetExtractor:");
        System.out.println(employeeDao.averageWithResultSetExtractor());
    }
}
