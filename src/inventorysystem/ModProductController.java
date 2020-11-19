/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cphtk
 */
public class ModProductController implements Initializable {

    @FXML
    private TextField productStockTxt;

    @FXML
    private TableColumn<Product, Integer> productId;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button saveChangesBtnProd;

    @FXML
    private Button searchBtn;

    @FXML
    private TableColumn<Product, Integer> productStock;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TableColumn<Product, String> productName;

    @FXML
    private TableColumn<Part, Integer> assPartIdColumn;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TableColumn<Part, String> delPartName;

    @FXML
    private TableView<Part> assPartTable1;

    @FXML
    private TextField searchProducts;

    @FXML
    private Button addAssPartBtn;

    @FXML
    private TableColumn<Part, String> assPartNameColumn;

    @FXML
    private Button deletePartBtn;

    @FXML
    private TableColumn<Part, Integer> delPartId;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TableColumn<Product, Double> productPrice;

    @FXML
    private TableView<Part> assPartTable;
          @FXML

            
     

    private void clearFields() {
        productIdTxt.setText(null);
        productNameTxt.setText(null);
        productPriceTxt.setText(null);
        productStockTxt.setText(null);
        productMinTxt.setText(null);
        productMaxTxt.setText(null);

    }
// error check for null values respond with dialog if a null value is found

    private boolean validateFields() {
        if ( productNameTxt.getText().isEmpty()
                | productPriceTxt.getText().isEmpty() | productStockTxt.getText().isEmpty()
                | productMinTxt.getText().isEmpty() | productMaxTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);

            return true;
        } else {
            return false;
        }

    }
     private boolean validateMin(){
        int min = Integer.parseInt(productMinTxt.getText());
        int max = Integer.parseInt(productMaxTxt.getText());
        if(min > max){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Min greater than Max");
            alert.setHeaderText(null);
            alert.setContentText("Minimum value is greater than maximum.");
            alert.showAndWait();
            return true;
        } else {
            return false;
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productIdTxt.setEditable(false);
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Inventory.allProducts);

        delPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        delPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
 
         
        assPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        assPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        assPartTable1.setItems(Inventory.getAllParts());
        
        
        assPartTable.setItems(Product.getAssociatedParts());
        
        ObservableList<Part> associatedParts = Product.getAssociatedParts();
        assPartTable.setItems(associatedParts);
        addAssPartBtn.setOnAction((ActionEvent value) -> {
            
         Product product = productTable.getSelectionModel().getSelectedItem();
         Part associatedPart = assPartTable1.getSelectionModel().getSelectedItem();
         product.addAssociatedPart(associatedPart);
         Product.setAssociatedParts(product.associatedParts);
         
        });
        deletePartBtn.setOnAction((ActionEvent value) -> {
            Product product = productTable.getSelectionModel().getSelectedItem();
            Part associatedPart = assPartTable.getSelectionModel().getSelectedItem();
            product.deleteAssociatedPart(associatedPart);
            assPartTable.setItems(product.getAssociatedParts());
        });
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
        saveChangesBtnProd.setOnAction((ActionEvent value) -> {
        int id;
            String name;
            double price;
            int stock;
            int min;
            int max;

            if (validateFields() == true) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Please fill out all fields.");
                alert.showAndWait();
            } else if(validateMin() == true){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a min < max.");
                alert.showAndWait();
            }
            else{
                Product product = productTable.getSelectionModel().getSelectedItem();
                id = productTable.getSelectionModel().getSelectedItem().getId();
                name = productNameTxt.getText();
                price = Double.parseDouble(productPriceTxt.getText());
                stock = Integer.parseInt(productStockTxt.getText());
                min = Integer.parseInt(productMinTxt.getText());
                max = Integer.parseInt(productMaxTxt.getText());
                
             
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                product.setStock(stock);
                product.setMin(min);
                product.setMax(max);
               Inventory.updateProduct(id -1, product);
                clearFields();
            }
            
        });
        
        
        
        //exit the stage on demand
        
        cancelBtn.setOnAction((ActionEvent value) -> {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Product not modified.");
                alert.showAndWait();


        });

    }
}



