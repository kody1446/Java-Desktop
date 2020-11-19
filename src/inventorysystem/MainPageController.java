/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author cphtk
 */
public class MainPageController implements Initializable {
    // declare the items in gui
    @FXML
    private TableColumn<Product, Integer> prodStockColumn;
    @FXML
    private TableColumn<Product, String> prodNameColumn;
    @FXML
    private TableColumn<Product, Double> prodPriceColumn;
    @FXML
    private TableColumn<Product, Integer> prodIdColumn;

    @FXML
    public TableView<Part> partTable;
    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, Integer> partStockColumn;

    @FXML
    private Button addPartBtn;

    @FXML
    public  TableView<Product> productTable;

    @FXML
    private Button addProduct;

    @FXML
    private Button srchProd;

    @FXML
    private Button deleteProd;
    @FXML
    private Button modProdBtn;

    @FXML
    private Button searchPartBtn;

    @FXML
    private Button deletePartBtn;

    @FXML
    private Button modPartBtn;
   
   @FXML
    private Button exitBtn;
   @FXML
   private TextField searchParts;
   @FXML
   private TextField searchProducts;

    @FXML
    void srchPart(ActionEvent event) {

    }
    
    
    // button events to open the various windows
    @FXML
    private void openAddPartWin(ActionEvent event) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
            Parent root = (Parent) myLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Parts");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot load new window...");
        }
    }
    @FXML
    private void openModPartWin(ActionEvent event) {
        try {

            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("ModPart.fxml"));
            Parent root = (Parent) myLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Modify Parts");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot load new window...");
        }
       

    }
    
    @FXML
    private void openAddProdWin(ActionEvent event) {
        try {

            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
            Parent root = (Parent) myLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Product");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot load new window...");
        }
    }
    @FXML
    private void openModProdWin(ActionEvent event) {
        try {

            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("ModProduct.fxml"));
            Parent root = (Parent) myLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Modify Product");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot load new window...");
        }
    }   
        
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // set up part table and update when parts are added/deleted
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partTable.setItems(Inventory.allParts);
        
        // set up product table and update when products are added/deleted
        prodIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productTable.setItems(Inventory.allProducts);
        
        
        
        // PRODUCT TABLE SORT
         // 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Product> filteredData = new FilteredList<>(Inventory.allProducts, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchProducts.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                            // Does not match.
				
				return product.getName().contains(lowerCaseFilter); 
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Product> sortedDataPR = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedDataPR.comparatorProperty().bind(productTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		 productTable.setItems(sortedDataPR);
                 
                 
                 
                 
                 //PART TABLE SORT
                  // 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Part> filteredDataPT = new FilteredList<>(Inventory.allParts, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchParts.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataPT.setPredicate(part -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                            // Does not match.
				
				return part.getName().contains(lowerCaseFilter); 
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Part> sortedData = new SortedList<>(filteredDataPT);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(partTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		 partTable.setItems(sortedData);
    

        
        deletePartBtn.setOnAction((ActionEvent value) -> {
            Part deletePart = partTable.getSelectionModel().getSelectedItem();
                Inventory.deletePart(deletePart);
                partTable.setItems(Inventory.allParts);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Part deleted.");
                alert.showAndWait();

        });
        deleteProd.setOnAction((ActionEvent value) -> {
            Product deleteProduct = productTable.getSelectionModel().getSelectedItem();
                Inventory.deleteProduct(deleteProduct);
                productTable.setItems(Inventory.allProducts);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Product deleted.");
                alert.showAndWait();

        });
        //exit the stage on demand
        exitBtn.setOnAction((ActionEvent value) -> {
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            stage.close();

        });
        
    }

}
