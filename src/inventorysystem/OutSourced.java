/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author cphtk
 */
public class OutSourced extends Part {

    String companyName;

    public OutSourced() {
           this.id = 0;
        this.name = "";
        this.price = 0.0;
        this.stock = 0;
        this.min = 0;
        this.max = 0;
        this.companyName = "";
    }

    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        setId(id);
       setName(name);
       setPrice(price);
       setStock(stock);
       setMin(min);
       setMax(max);
       setCompanyName(companyName);
    }

   

    public void setCompanyName(String companyName) {
        this.companyName = companyName;

    }

    public String getCompanyName() {
        return this.companyName;
    }

}
