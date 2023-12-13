package com.example.jdbccallbackdemo.dao;

import com.example.jdbccallbackdemo.ds.Employee;
import lombok.SneakyThrows;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
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
    public double averageWithRowCallBack(){
        CustomRollCallBackHandler handler=
                new CustomRollCallBackHandler();
        this.jdbcTemplate
                .query("select salary from employee",
                        handler);
        return handler.average();
    }

    public double averageWithResultSetExtractor(){
        return jdbcTemplate
                .query("select salary from employee",
                        new CustomResultSetExtractor());
    }

    private class CustomResultSetExtractor implements ResultSetExtractor<Double>{

        @Override
        public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
            double total =0;
            int count =0;
            while (rs.next()){
                total += rs.getDouble("salary");
                count++;
            }
            return total/(double) count;
        }
    }
    private static class CustomRollCallBackHandler implements RowCallbackHandler {
        double total;
        int count;
        @Override
        public void processRow(ResultSet rs) throws SQLException {
            total += rs.getDouble("salary");
            count++;
        }
        public double average(){
            return total/(double) count;
        }
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
