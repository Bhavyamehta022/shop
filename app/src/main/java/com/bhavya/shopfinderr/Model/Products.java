package com.bhavya.shopfinderr.Model;

public class Products {

    private String Name,Description,Price,Image,Category;
    public Products(){


    }

    public Products(String name, String description, String price, String image, String category) {
        Name = name;
        Description = description;
        Price = price;
        Image = image;
        Category = category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
