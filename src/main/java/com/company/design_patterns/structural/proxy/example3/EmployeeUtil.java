package com.company.design_patterns.structural.proxy.example3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeUtil {

    public static List<Employee> getEmployeelist(){

        Employee bahadir = new Employee("bahadir", "123", false);
        Employee ayse = new Employee("ayse", "1234", false);
        Employee yusuf = new Employee("yusuf", "12345", true);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(bahadir);
        employeeList.add(ayse);
        employeeList.add(yusuf);

        return employeeList;

    }

    public static Map<String, Employee> getByUsername(){

        List<Employee> employeeList = getEmployeelist();

        Map<String, Employee> map = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            map.put(emp.getUsername(), emp);
        }

        return map;
    }

    public static Employee getEmployeeByUsername(String username){

        Map<String, Employee> map = getByUsername();

        Employee emp = map.get(username);

        return emp;
    }

    public static boolean isUserValid(String username, String password) {

        boolean isUserValid = false;

        Employee employee = getEmployeeByUsername(username);

        if (employee != null){

            isUserValid = employee.getPassword().equals(password);
        }

        return isUserValid;
    }
}
