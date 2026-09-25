package com.company.design_patterns.behavioral.template_method;

import java.math.BigDecimal;

public class CsvReport extends ReportGenerator {

    @Override
    protected String header() {
        return "region,product,amount\n";
    }

    @Override
    protected String row(Sale sale) {
        return sale.region() + "," + sale.product() + "," + sale.amount() + "\n";
    }

    @Override
    protected String footer(BigDecimal total) {
        return "TOTAL,," + total + "\n";
    }
}
