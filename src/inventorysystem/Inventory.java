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
public class Inventory {

     static ObservableList<Part> allParts = FXCollections.observableArrayList();
     static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    static void addPart(InHouse part) {
        allParts.add(part);
    }

    static void addPart(OutSourced part) {
        allParts.add(part);
        
    }

    static void addProduct(Product product) {
        allProducts.add(product);
    }

    Part lookUpPart(int partId) {
        int i;
        Part result = null;
        for (i = 0; i <= allParts.size(); ++i) {
            if (allParts.equals(partId)) {
                result = (Part) allParts;

            }
        }
        return result;
    }

    Product lookUpProduct(int productId) {
        int i;
        Product result = null;
        for (i = 0; i <= allParts.size(); ++i) {
            if (allParts.equals(productId)) {
                result = (Product) allProducts;

            }
        }
        return result;
    }

    ObservableList<Part> lookUpPart(String partName) {
        int i;
        Part result = null;
        for (i = 0; i <= allParts.size(); ++i) {
            if (allParts.equals(partName)) {
                result = (Part) allParts;

            }
        }
        return (ObservableList<Part>) result;
    }

    ObservableList<Product> lookUpProduct(String productName) {
        int i;
        Product result = null;
        for (i = 0; i <= allParts.size(); ++i) {
            if (productName.equals(allProducts)) {
                result = (Product) allProducts;

            }
        }
        return (ObservableList<Product>) result;
    }

    static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
        
    }

    static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);

    }

    static void deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
    }

    static void deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
        }
    }

    static ObservableList<Part> getAllParts() {
        return FXCollections.observableArrayList(allParts);
    }

    static ObservableList<Product> getAllProducts() {
        return FXCollections.observableArrayList(allProducts);
    }
    
  

}
