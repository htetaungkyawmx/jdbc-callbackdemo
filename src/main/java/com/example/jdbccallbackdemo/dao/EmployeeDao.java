package com.example.jdbccallbackdemo.dao;

import com.example.jdbccallbackdemo.ds.Employee;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    @SneakyThrows
    private Employee mapEmployee(ResultSet rs,int i){
        return new Employee(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getDate("hired_date"),
                rs.getDouble("salary")
        );
    }

    public List<Employee> findAllEmployee(){
        return jdbcTemplate.query(
                "select * from employee",
                this::mapEmployee
        );
    }

    public EmployeeDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    public int countEmployee(){
        return jdbcTemplate.queryForObject("select count(id) from employee",
                Integer.class);
    }
}
