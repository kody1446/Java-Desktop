/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
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
public class ModPartController implements Initializable {

    @FXML
    private TextField stockTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField idTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField minTxt;

    @FXML
    private Button saveChangesBtn;

    @FXML
    private TextField nameTxt;

    @FXML
    private RadioButton OutSourcedPart;

    @FXML
    private TextField CompanyNametxt;

    @FXML
    private RadioButton InHousePart;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField MachineIdtxt;
    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private TableColumn<Part, Integer> partStockColumn;

    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    void IfInHouseSelected(ActionEvent event) {

    }

    @FXML
    void IfOutSourceSelected(ActionEvent event) {

    }

    private void clearFields() {
        idTxt.setText(null);
        nameTxt.setText(null);
        priceTxt.setText(null);
        stockTxt.setText(null);
        minTxt.setText(null);
        maxTxt.setText(null);
        MachineIdtxt.setText(null);
        OutSourcedPart.setText(null);

    }

      private boolean validateFields() {
        if ( nameTxt.getText().isEmpty()
                | priceTxt.getText().isEmpty() | stockTxt.getText().isEmpty()
                | minTxt.getText().isEmpty() | maxTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();

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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Min greater than Max");
            alert.setHeaderText(null);
            alert.setContentText("Minimum value is greater than maximum.");
            alert.showAndWait();
            return true;
        } else {
            return false;
        }
    };
    @FXML
    void cancelBtnClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partTable.setItems(Inventory.allParts);
        
        idTxt.setEditable(false);
        saveChangesBtn.setOnAction((ActionEvent value)-> {

        int id;
        String name;
        double price;
        int stock;
        int min;
        int max;
        if (validateFields() == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Please fill out all fields.");
                alert.showAndWait();
            } 
        else if(validateMin() == true){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a min < max.");
                alert.showAndWait();
            }
        else {
            
        id = partTable.getSelectionModel().getSelectedItem().getId();
        name = nameTxt.getText();
        price = Integer.parseInt(priceTxt.getText());
        stock = Integer.parseInt(stockTxt.getText());
        min = Integer.parseInt(minTxt.getText());
        max = Integer.parseInt(maxTxt.getText());

        if (InHousePart.isSelected() == true) {
            int machineId;
            machineId = Integer.parseInt(MachineIdtxt.getText());
            InHouse part = (InHouse) partTable.getSelectionModel().getSelectedItem();
            part.setId(id);
            part.setName(name);
            part.setPrice(price);
            part.setStock(stock);
            part.setMin(min);
            part.setMax(max);
            part.setMachineId(machineId);
            Inventory.updatePart(id -1 , part);
            clearFields();

        }
        else if (OutSourcedPart.isSelected() == true) {
            String companyName;
            companyName = CompanyNametxt.getText();
            OutSourced part = (OutSourced) partTable.getSelectionModel().getSelectedItem();
            part.setId(id);
            part.setName(name);
            part.setPrice(price);
            part.setStock(stock);
            part.setMin(min);
            part.setMax(max);
            part.setCompanyName(companyName);
            Inventory.updatePart(id -1, part);
            clearFields();
        }
        }
    });

//exit the stage on demand
        cancelBtn.setOnAction((ActionEvent value) -> {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Part not modified");
                alert.showAndWait();


        });
    }

}
