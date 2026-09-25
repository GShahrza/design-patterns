package com.company.design_patterns.creational.builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Immutable obyekt: yalnız {@link ProductBuilder} vasitəsilə yaradılır, setter yoxdur.
 * Builder çoxlu (xüsusən optional) parametrli konstruktorların ("telescoping constructor") əvəzidir.
 */
public final class Product {

    private final Long id;
    private final String name;
    private final LocalDateTime date;
    private final boolean inStock;
    private final String description;
    private final BigDecimal price;

    private Product(ProductBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.date = builder.date;
        this.inStock = builder.inStock;
        this.description = builder.description;
        this.price = builder.price;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isInStock() {
        return inStock;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", inStock=" + inStock +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public static final class ProductBuilder {

        private Long id;
        private String name;
        private LocalDateTime date = LocalDateTime.now();
        private boolean inStock = true;
        private String description = "";
        private BigDecimal price = BigDecimal.ZERO;

        private ProductBuilder() {
        }

        public ProductBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public ProductBuilder inStock(boolean inStock) {
            this.inStock = inStock;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        /** Validasiya bir yerdə: yanlış vəziyyətdə obyekt heç vaxt yaranmır. */
        public Product build() {
            Objects.requireNonNull(name, "name is required");
            if (price.signum() < 0) {
                throw new IllegalStateException("price must not be negative");
            }
            return new Product(this);
        }
    }
}
