package com.company.design_patterns.structural.proxy.example3;

import java.math.BigDecimal;

public class ManagerProxy implements CompanyInformation{

    private TruthManager truthManager;

    private String username;
    private String password;

    public ManagerProxy(String username,String password){
        this.truthManager = new TruthManager();
        this.username = username;
        this.password = password;
    }

    private boolean isUserManager(){
        boolean isUserManager = false;

        boolean isUserValid = EmployeeUtil.isUserValid(username,password);

        if (isUserValid){
            Employee emp = EmployeeUtil.getEmployeeByUsername(username);

            isUserManager = emp.isManager();
        }

        return isUserManager;
    }
    @Override
    public BigDecimal getCiro() {

        boolean userManager = isUserManager();

        if (userManager){
            return truthManager.getCiro();
        }
        return null;
    }
}
