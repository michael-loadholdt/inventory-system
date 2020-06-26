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
import javafx.scene.control.Alert.AlertType;
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
public class MainScreenController implements Initializable {
    
    Stage stage;
    Parent scene;
   
    
    @FXML
    private TextField partsSearchText;
     
    @FXML
    private TextField productSearchText;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part,Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInventoryLevelCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;
  
    @FXML
    public TableView<Part> getPartsTableView(){
        
        return partsTableView;
    
    }
    
    @FXML
    void deleteSelectedPart(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Part Delete");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int selectedPartIndex = partsTableView.getSelectionModel().getSelectedIndex();
            Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
            Inventory.deletePart(selectedPartIndex, selectedPart);
        }

    }

    @FXML
    void deleteSelectedProduct(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Productt Delete");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int selectedPartIndex = partsTableView.getSelectionModel().getSelectedIndex();
            Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
            Inventory.deletePart(selectedPartIndex, selectedPart);
        }
        
       //int selectedProductIndex = productTableView.getSelectionModel().getSelectedIndex();
       Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
       Inventory.deleteProduct(selectedProduct);
    }

    @FXML
    void exitApplication(ActionEvent event) {
        
        System.exit(0);

    }

    @FXML
    void goToAddPartScreen(ActionEvent event) throws IOException {
              
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void goToAddProductScreen(ActionEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void goToModifyPartScreen(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ModifyPart.fxml"));
        loader.load();
        
        ModifyPartController modifyPartController = loader.getController();
        modifyPartController.sendPart(partsTableView.getSelectionModel().getSelectedItem());
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
        

    }

    @FXML
    void goToModifyProductScreen(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
        loader.load();
        
        ModifyProductController modifyProductController = loader.getController();
        modifyProductController.sendProduct(productTableView.getSelectionModel().getSelectedItem());
        
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void mainScreenPartsSearch(ActionEvent event) {
        
        partsTableView.getSelectionModel().select(Inventory.lookupPart
        (Integer.parseInt(partsSearchText.getText())-1));
    
    }

    @FXML
    void mainScreenProductSearch(ActionEvent event) {
        
        productTableView.getSelectionModel().select(Inventory.lookupProduct
        (Integer.parseInt(productSearchText.getText())-1));

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        partsTableView.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productTableView.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }    
    
}
