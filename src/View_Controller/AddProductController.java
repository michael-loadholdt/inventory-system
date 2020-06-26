/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import inventory_system_michael_loadholdt.Inventory_System_Michael_Loadholdt;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
public class AddProductController implements Initializable {
    
    Stage stage;
    Parent scene;
    private ArrayList<Part> associatedPartsList = new ArrayList();
    
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
    void addPartToProduct(ActionEvent event) {
       
       Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
       associatedPartsTableView.getItems().add(selectedPart);
       associatedPartsList.add(selectedPart);
       

    }

    @FXML
    void deletePartFromProduct(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Removing Associated Part");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

        associatedPartsTableView.getItems().remove(associatedPartsTableView.getSelectionModel().getSelectedItem());
        associatedPartsList.remove((partTableView.getSelectionModel().getSelectedItem()));
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
        
        --Inventory_System_Michael_Loadholdt.initialProductID;
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        }

    }

    @FXML
    void saveNewProduct(ActionEvent event) throws IOException {
        
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
               for(int i = 0; i < associatedPartsTableView.getItems().size(); ++i){
                    product.addAssociatedPart(associatedPartsList.get(i));
                }
                Inventory.addProduct(product);
        
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       ++Inventory_System_Michael_Loadholdt.initialProductID;
       productID.setText(Integer.toString(Inventory_System_Michael_Loadholdt.initialProductID));
       
        
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
