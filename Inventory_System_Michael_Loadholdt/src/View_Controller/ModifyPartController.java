/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
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
public class ModifyPartController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private ToggleGroup modifyPartToggleGroup;

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
    private TextField machineIDCompanyName;

    @FXML
    private Label companyLabel;

    @FXML
    void displayCompanyNameLabel(MouseEvent event) {
        
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
           stage = (Stage)((Button)event.getSource()).getScene().getWindow();
           scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml"));
           stage.setScene(new Scene(scene));
           stage.show();
        } 

    }

    @FXML
    void savePartChanges(ActionEvent event) throws IOException {
        
        if(inHouseRadioButton.isSelected()){
            int ID = Integer.parseInt(partID.getText());
            String name = partName.getText();
            int stock = Integer.parseInt(partInventory.getText());
            double price = Double.parseDouble(partPrice.getText());
            int max = Integer.parseInt(partMax.getText());
            int min = Integer.parseInt(partMin.getText());
            int machineID = Integer.parseInt(machineIDCompanyName.getText());
            if((stock > max)  || (stock < min)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Inventory Error");
                alert.setContentText("Inventory on hand is not in range!");

                alert.showAndWait();
                
            }
            
            else{
                InHousePart part = new InHousePart(ID, name, price, stock, min, max, machineID);
                       
                for(int i = 0; i < Inventory.getAllProducts().size(); ++i){
                    if(Inventory.getAllParts().get(i).getID() == part.getID()){
                        Inventory.getAllParts().set(i, part);
                        break;
                    }
                
                
                }               
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Inventory Error");
                alert.setContentText("Inventory on hand is not in range!");

                alert.showAndWait();
                
            }
            else{
                OutsourcedPart part = new OutsourcedPart(ID, name, price, stock, min, max, companyName);
                       
                for(int i = 0; i < Inventory.getAllProducts().size(); ++i){
                    if(Inventory.getAllParts().get(i).getID() == part.getID()){
                        Inventory.getAllParts().set(i, part);
                        break;
                    }
                }
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show(); 
            }            
            
        }
        


    }
    
    public void sendPart(Part part){
        
        if(part instanceof InHousePart){
            InHousePart modifyInHousePart = (InHousePart) part;
            partID.setText(String.valueOf(part.getID()));
            partName.setText(part.getName());
            partInventory.setText(String.valueOf(part.getStock()));
            partPrice.setText(String.valueOf(part.getPrice()));
            partMax.setText(String.valueOf(part.getMax()));
            partMin.setText(String.valueOf(part.getMin()));
            companyLabel.setText("Machine ID:");
            inHouseRadioButton.setSelected(true);
            machineIDCompanyName.setText(String.valueOf(modifyInHousePart.getMachineID()));
        }
        
        if(part instanceof OutsourcedPart){
            OutsourcedPart modifyOutsourcedPart = (OutsourcedPart) part;
            partID.setText(String.valueOf(part.getID()));
            partName.setText(part.getName());
            partInventory.setText(String.valueOf(part.getStock()));
            partPrice.setText(String.valueOf(part.getPrice()));
            partMax.setText(String.valueOf(part.getMax()));
            partMin.setText(String.valueOf(part.getMin()));
            companyLabel.setText("Company Name:");
            outsourcedRadioButton.setSelected(true);
            machineIDCompanyName.setText(String.valueOf(modifyOutsourcedPart.getCompanyName()));
        }
        
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    
    
}
