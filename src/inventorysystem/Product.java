/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cphtk
 */
public class Product {
   static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
   
    int id;
    String name;
    double price;
    int stock;
    int min;
    int max;

   

    public  Product() {
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.stock = 0;
        this.min = 0;
        this.max = 0;
    }

    public Product(int id, String name, double price, int stock, int min, int max ) {
        setId(id);
       setName(name);
       setPrice(price);
       setStock(stock);
       setMin(min);
       setMax(max);
    }
    
    public static void setAssociatedParts(ObservableList<Part> associatedParts){
        associatedParts = FXCollections.observableArrayList();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }



     public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    static public void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }

    static public  ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

   

       
    

}
