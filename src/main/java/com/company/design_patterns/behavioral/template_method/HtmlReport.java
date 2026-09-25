package com.company.design_patterns.behavioral.template_method;

import java.math.BigDecimal;
import java.util.List;

/** HTML hesabatı yalnız verilmiş regionu göstərir — {@link #filter} hook-u override olunub. */
public class HtmlReport extends ReportGenerator {

    private final String region;

    public HtmlReport(String region) {
        this.region = region;
    }

    @Override
    protected List<Sale> filter(List<Sale> sales) {
        return sales.stream().filter(s -> s.region().equals(region)).toList();
    }

    @Override
    protected String header() {
        return "<table>\n  <tr><th colspan=2>" + region + "</th></tr>\n";
    }

    @Override
    protected String row(Sale sale) {
        return "  <tr><td>" + sale.product() + "</td><td>" + sale.amount() + "</td></tr>\n";
    }

    @Override
    protected String footer(BigDecimal total) {
        return "  <tr><td><b>Total</b></td><td><b>" + total + "</b></td></tr>\n</table>\n";
    }
}
