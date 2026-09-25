package com.company.design_patterns.behavioral.template_method;

import java.math.BigDecimal;
import java.util.List;

/**
 * Template Method: {@link #generate} alqoritmin skeletini müəyyən edir (final — dəyişdirilə bilməz).
 * Alt siniflər yalnız konkret addımları (header/row/footer) override edir.
 */
public abstract class ReportGenerator {

    public final String generate(List<Sale> sales) {
        List<Sale> filtered = filter(sales);
        StringBuilder out = new StringBuilder();
        out.append(header());
        for (Sale sale : filtered) {
            out.append(row(sale));
        }
        BigDecimal total = filtered.stream().map(Sale::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
        out.append(footer(total));
        return out.toString();
    }

    protected abstract String header();

    protected abstract String row(Sale sale);

    protected abstract String footer(BigDecimal total);

    /** Hook: default davranışı var, alt sinif istəsə override edir. */
    protected List<Sale> filter(List<Sale> sales) {
        return sales;
    }
}
