/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import inventory_system_michael_loadholdt.Inventory_System_Michael_Loadholdt;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author micha
 */
public class AddPartController implements Initializable {
    
    Stage stage;
    Parent scene;
    
       
    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private ToggleGroup addPartToggleGroup;

    @FXML
    private RadioButton outsourcedRadioButton;
    

    @FXML
    private TextField partID;

    @FXML
    private TextField partName;

    @FXML
    private TextField partInventory;

    @FXML
    private TextField partPrice;

    @FXML
    private TextField partMax;

    @FXML
    private TextField partMin;

    @FXML
    private Label companyLabel;

    @FXML
    private TextField machineIDCompanyName;

    @FXML
    void displayCompanyLabel(MouseEvent event) {
        
        companyLabel.setText("Company Name:");

    }

    @FXML
    void displayMachineIDLabel(MouseEvent event) {
        
        companyLabel.setText("Machine ID:");

    }

    @FXML
    void returnToMainScreen(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Returning to Main Screen");
        alert.setContentText("Are you sure? All changes will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        --Inventory_System_Michael_Loadholdt.initialPartID;
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        } 
        

    }

    @FXML
    void saveNewPart(ActionEvent event) throws IOException {
        
        if(inHouseRadioButton.isSelected()){
            int ID = Integer.parseInt(partID.getText());
            String name = partName.getText();
            int stock = Integer.parseInt(partInventory.getText());
            double price = Double.parseDouble(partPrice.getText());
            int max = Integer.parseInt(partMax.getText());
            int min = Integer.parseInt(partMin.getText());
            int machineID = Integer.parseInt(machineIDCompanyName.getText());
            if((stock > max)  || (stock < min)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Inventory Error");
                alert.setContentText("Inventory on hand is not in range!");

                alert.showAndWait();
                
            }
            else{    
            Inventory.addPart(new InHousePart(ID, name, price, stock, min, max, machineID));
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            }
        }
        
        if(outsourcedRadioButton.isSelected()){
            int ID = Integer.parseInt(partID.getText());
            String name = partName.getText();
            int stock = Integer.parseInt(partInventory.getText());
            double price = Double.parseDouble(partPrice.getText());
            int max = Integer.parseInt(partMax.getText());
            int min = Integer.parseInt(partMin.getText());
            String companyName = machineIDCompanyName.getText();
            
            if((stock > max)  || (stock < min)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Inventory Error");
                alert.setContentText("Inventory on hand is not in range!");

                alert.showAndWait();
                
            }
            else{
            Inventory.addPart(new OutsourcedPart(ID, name, price, stock, min, max, companyName));
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        }


    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ++Inventory_System_Michael_Loadholdt.initialPartID;
       partID.setText(Integer.toString(Inventory_System_Michael_Loadholdt.initialPartID));
       
    }    
   
    
}
