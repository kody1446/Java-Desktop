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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cphtk
 */
public class AddPartController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    void IfInHouseSelected(ActionEvent event) {

    }

    @FXML
    void IfOutSourceSelected(ActionEvent event) {

    }

    @FXML
    void SavePart(ActionEvent action) {

    }

    @FXML
    void cancelBtnClicked(ActionEvent event) {

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
            Alert alert = new Alert(AlertType.WARNING);
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
        MachineIdtxt.disableProperty().bind(OutSourcedPart.selectedProperty());
        CompanyNametxt.disableProperty().bind(InHousePart.selectedProperty());
        OutSourcedPart.disableProperty().bind(InHousePart.selectedProperty());
        InHousePart.disableProperty().bind(OutSourcedPart.selectedProperty());
        idTxt.setEditable(false);
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
                id = Inventory.allParts.size() + 1;
                name = nameTxt.getText();
                price = Double.parseDouble(priceTxt.getText());
                stock = Integer.parseInt(stockTxt.getText());
                min = Integer.parseInt(minTxt.getText());
                max = Integer.parseInt(maxTxt.getText());
            
             if (InHousePart.isSelected() == true) {
                    int machineId;
                    machineId = Integer.parseInt(MachineIdtxt.getText());
                    InHouse inhouse = new InHouse();
                    inhouse.setId(id);
                    inhouse.setName(name);
                    inhouse.setPrice(price);
                    inhouse.setStock(stock);
                    inhouse.setMin(min);
                    inhouse.setMax(max);
                    inhouse.setMachineId(machineId);
                    Inventory.addPart(inhouse);
                    clearFields();

                }
             else if (OutSourcedPart.isSelected() == true) {
                    String companyName;
                    companyName = CompanyNametxt.getText();
                    OutSourced outsourced = new OutSourced();
                    outsourced.setId(id);
                    outsourced.setName(name);
                    outsourced.setPrice(price);
                    outsourced.setStock(stock);
                    outsourced.setMin(min);
                    outsourced.setMax(max);
                    outsourced.setCompanyName(companyName);
                    Inventory.addPart(outsourced);
                    clearFields();
                }

            }

        });
        
        //exit the stage on demand
        cancelBtn.setOnAction((ActionEvent value) -> {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Part not added");
                alert.showAndWait();

        });

    }

}
