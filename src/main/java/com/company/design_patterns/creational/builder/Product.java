package com.company.design_patterns.creational.builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {

    private Long id;
    private String name;
    private LocalDateTime date;
    private Boolean inStock;
    private String description;
    private BigDecimal price;

    public Product() {
    }

    public Product(Long id, String name, LocalDateTime date, Boolean inStock, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.inStock = inStock;
        this.description = description;
        this.price = price;
    }

    public Product(ProductBuilder productBuilder) {
        this.id = productBuilder.id;
        this.name = productBuilder.name;
        this.date = productBuilder.date;
        this.inStock = productBuilder.inStock;
        this.description = productBuilder.description;
        this.price = productBuilder.price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static ProductBuilder builder(){
        return new ProductBuilder();
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

    public static class ProductBuilder{

        private Long id;
        private String name;
        private LocalDateTime date;
        private Boolean inStock;
        private String description;
        private BigDecimal price;

        public ProductBuilder id(Long id){
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name){
            this.name = name;
            return this;
        }

        public ProductBuilder date(LocalDateTime date){
            this.date = date;
            return this;
        }

        public ProductBuilder inStock(Boolean inStock){
            this.inStock = inStock;
            return this;
        }

        public ProductBuilder description(String description){
            this.description = description;
            return this;
        }

        public ProductBuilder price(BigDecimal price){
            this.price = price;
            return this;
        }

        public Product build(){
            return new Product(this);
            //return new Product(this.id,this.name,this.date,this.inStock,this.description,this.price);
        }

    }
}
