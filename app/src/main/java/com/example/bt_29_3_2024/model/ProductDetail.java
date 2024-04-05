package com.example.bt_29_3_2024.model;

public class ProductDetail {
    private String meal;
    private String area;
    private String instructions;
    private String strmealthumb;
    private double price;

    public ProductDetail(String meal, String area, String instructions, String strmealthumb, double price) {
        this.meal = meal;
        this.area = area;
        this.instructions = instructions;
        this.strmealthumb = strmealthumb;
        this.price = price;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getStrmealthumb() {
        return strmealthumb;
    }

    public void setStrmealthumb(String strmealthumb) {
        this.strmealthumb = strmealthumb;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
