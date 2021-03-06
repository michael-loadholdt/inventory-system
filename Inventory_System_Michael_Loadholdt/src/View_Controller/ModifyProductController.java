/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class ModifyProductController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    
    @FXML
    private TextField productID;

    @FXML
    private TextField productName;

    @FXML
    private TextField productStock;

    @FXML
    private TextField productMin;

    @FXML
    private TextField productPrice;

    @FXML
    private TextField productMax;
    
    @FXML
    private TextField productSearchField;
    
    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partPartIDCol;

    @FXML
    private TableColumn<Part, String> partPartNameCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Part> associatedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> associatedPartsIDCol;

    @FXML
    private TableColumn<Part, String> associatedPartsNameCol;

    @FXML
    private TableColumn<Part, Integer> associatedPartsInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> associatedPartsPriceCol;

    @FXML
    void addParttoProduct(ActionEvent event) {
       
       Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
       associatedPartsTableView.getItems().add(selectedPart);
       
    }

    @FXML
    void deletePartFromProduct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Part Delete");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           associatedPartsTableView.getItems().remove(associatedPartsTableView.getSelectionModel().getSelectedItem());
        }
        
        

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
    void saveProductChanges(ActionEvent event) throws IOException {
            
        int ID = Integer.parseInt(productID.getText());
        String name = productName.getText();
        int stock = Integer.parseInt(productStock.getText());
        double price = Double.parseDouble(productPrice.getText());
        int max = Integer.parseInt(productMax.getText());
        int min = Integer.parseInt(productMin.getText());
        
            if((stock > max)  || (stock < min)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Inventory Error");
                alert.setContentText("Inventory on hand is not in range!");

                alert.showAndWait();
                
            }
            else{
                Product product = new Product(ID, name, price, stock, min, max);
                
                associatedPartsTableView.getItems().forEach((part) -> {
                    product.addAssociatedPart(part);
            });
                
                for(int i = 0; i < Inventory.getAllProducts().size(); ++i){
                    if(Inventory.getAllProducts().get(i).getID() == product.getID()){
                        Inventory.getAllProducts().set(i, product);
                        break;
                    }
                
                
                }
        
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }    

    }

    @FXML
    void searchPartsList(ActionEvent event) {
        
        partTableView.getSelectionModel().select(Inventory.lookupPart
        (Integer.parseInt(productSearchField.getText())-1));
            
    }
    
    public void sendProduct(Product product){
        
            productID.setText(String.valueOf(product.getID()));
            productName.setText(product.getName());
            productStock.setText(String.valueOf(product.getStock()));
            productPrice.setText(String.valueOf(product.getPrice()));
            productMax.setText(String.valueOf(product.getMax()));
            productMin.setText(String.valueOf(product.getMin()));
            associatedPartsTableView.setItems(product.getAllAssociatedParts());
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        partTableView.setItems(Inventory.getAllParts());
        partPartIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        associatedPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        associatedPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartsInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }    
    
}
