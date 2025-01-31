package me.thomassuebwicha.domain;

import java.util.Objects;

public class Product {
    protected String name;
    //  grams
    protected String weight;
    //  Dollars
    protected double statedPrice;
    //  Dollars
    protected double savingPrice;
    //  Dollars
    protected double wasPrice;

    protected String ratePrice;

    protected String supermarket;

    public Product() {
        this.name = "unnamed";
        this.weight = "00.00";
        this.statedPrice = 00.00;
        this.savingPrice = 00.00;
        this.wasPrice = 00.00;
        this.ratePrice = "ratePrice";
        this.supermarket = "NA";
    }

    public Product(String name, String weight, double statedPrice, double savingPrice, double wasPrice, String ratePrice, String supermarket) {
        this.name = name;
        this.weight = weight;
        this.statedPrice = statedPrice;
        this.savingPrice = savingPrice;
        this.wasPrice = wasPrice;
        this.ratePrice = ratePrice;
        this.supermarket = supermarket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public double getStatedPrice() {
        return statedPrice;
    }

    public void setStatedPrice(double statedPrice) {
        this.statedPrice = statedPrice;
    }

    public double getSavingPrice() {
        return savingPrice;
    }

    public void setSavingPrice(double savingPrice) {
        this.savingPrice = savingPrice;
    }

    public double getWasPrice() {
        return wasPrice;
    }

    public void setWasPrice(double wasPrice) {
        this.wasPrice = wasPrice;
    }

    public String getRatePrice() {
        return ratePrice;
    }

    public void setRatePrice(String ratePrice) {
        this.ratePrice = ratePrice;
    }

    public String getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(statedPrice, product.statedPrice) == 0 && Double.compare(savingPrice, product.savingPrice) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", weight='" + weight + '\'' +
                ", statedPrice=" + statedPrice +
                ", savingPrice=" + savingPrice +
                ", wasPrice=" + wasPrice +
                ", ratePrice='" + ratePrice + '\'' +
                ", supermarket='" + supermarket + '\'' +
                '}';
    }
}

