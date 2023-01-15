package edu.school21.models;

import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private double price;

    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {

        return "Product {" +
                "id=" + id +
                ", name='" + name + "'" +
                ", price=" + price + "}";

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;

        return Double.compare(product.price, price) == 0 && id.equals(product.id) && name.equals(product.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
