package com.company.design_patterns.structural.proxy.example3;

import java.math.BigDecimal;

public class TruthManager implements CompanyInformation{
    @Override
    public BigDecimal getCiro() {
        return BigDecimal.valueOf(10000);
    }
}
