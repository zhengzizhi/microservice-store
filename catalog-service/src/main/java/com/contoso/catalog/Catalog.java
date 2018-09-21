package com.contoso.catalog;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.contoso.product.Product;

public class Catalog implements Serializable {

	private static final long serialVersionUID = -8019284967393750801L;

	private Long id;

    private Long catalogNumber;

    private Set<Product> products = new HashSet<>();

    private String name;

    public Catalog() {
    }

    public Catalog(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(Long catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", catalogNumber=" + catalogNumber +
                ", products=" + products +
                ", name='" + name + '\'' +
                '}';
    }
}
