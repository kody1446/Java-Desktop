/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProductController implements Initializable {

    @FXML
    private TableColumn<Part, Integer> assPartStock;

    @FXML
    private TableColumn<Part, String> assPartName;

    @FXML
    private TableColumn<Part, Double> assPartPrice;
    @FXML
    private TableColumn<Part, Integer> assPartId;

    @FXML
    private TableColumn<Part, Integer> partStockColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    public TableView<Part> assPartTable;

    @FXML
    public TableView<Part> partTable;
    @FXML
    private Button cancelBtn;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TextField idTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField stockTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private Button saveChangesBtn;
    @FXML
    private Button addAssPartBtn;
    @FXML
    private Button deleteAssPartBtn;
    
    @FXML
    private TextField searchParts;

    // auto clear fields after hitting "save"
    private void clearFields() {
        idTxt.setText(null);
        nameTxt.setText(null);
        priceTxt.setText(null);
        stockTxt.setText(null);
        minTxt.setText(null);
        maxTxt.setText(null);

    }

    // error check for null values respond with dialog if a null value is found
    private boolean validateFields() {
        if (nameTxt.getText().isEmpty()
                | priceTxt.getText().isEmpty() | stockTxt.getText().isEmpty()
                | minTxt.getText().isEmpty() | maxTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);

            return true;
        } else {
            return false;
        }

    }

    ;
    private boolean validateMin(){
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
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
        Product product = new Product();
        idTxt.setEditable(false);
        // set up all parts table for easy selection of parts to add to associated parts for the product.
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable.setItems(Inventory.getAllParts());

        //set up associated parts table but do not set the table values until the user adds a part.
        assPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        assPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        assPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //load table values in associated parts table 
        addAssPartBtn.setOnAction((ActionEvent value) -> {
            
            Part associatedPart = partTable.getSelectionModel().getSelectedItem();
            product.addAssociatedPart(associatedPart);
            Product.setAssociatedParts(product.associatedParts);
            
        });

        //parse information input by the user and add it to a product object while error checking to make sure all fields are filled out.
        saveChangesBtn.setOnAction((ActionEvent value) -> {
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
                id = Inventory.allProducts.size() + 1;
                name = nameTxt.getText();
                price = Double.parseDouble(priceTxt.getText());
                stock = Integer.parseInt(stockTxt.getText());
                min = Integer.parseInt(minTxt.getText());
                max = Integer.parseInt(maxTxt.getText());

             
                product.setId(id);
                product.setName(name);
                product.setStock(stock);
                product.setPrice(price);
                product.setMin(min);
                product.setMax(max);
                Inventory.addProduct(product);
                clearFields();
            }
        });
        assPartTable.setItems(product.getAssociatedParts());
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

        // Give option to delete a selected product from associated products table if a mistake was noticed.
        deleteAssPartBtn.setOnAction((ActionEvent value) -> {
            
                Part associatedPart = assPartTable.getSelectionModel().getSelectedItem();
                Product.deleteAssociatedPart(associatedPart);
                assPartTable.setItems(product.getAssociatedParts());
        });
        // close stage on user's demand
        cancelBtn.setOnAction((ActionEvent value) -> {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Product not added");
                alert.showAndWait();


        });

    }
}
