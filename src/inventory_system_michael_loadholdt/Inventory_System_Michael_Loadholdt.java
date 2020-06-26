/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system_michael_loadholdt;


import Model.InHousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author micha
 */
public class Inventory_System_Michael_Loadholdt extends Application {

    public static int initialPartID = 10;
    public static int initialProductID = 5;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        InHousePart part1 = new InHousePart(1, "Part 1", 5.00, 1, 0, 10, 12345);
        InHousePart part2 = new InHousePart(2, "Part 2", 10.00, 1, 0, 10, 12345);
        InHousePart part3 = new InHousePart(3, "Part 3", 15.00, 1, 0, 10, 12345);
        InHousePart part4 = new InHousePart(4, "Part 4", 20.00, 1, 0, 10, 12345);
        InHousePart part5 = new InHousePart(5, "Part 5", 25.00, 1, 0, 10, 12345);
        
        OutsourcedPart part6 = new OutsourcedPart(6, "Part 6", 5.00, 1, 0, 10, "ACME Inc." );
        OutsourcedPart part7 = new OutsourcedPart(7, "Part 7", 10.00, 1, 0, 10, "ACME Inc." );
        OutsourcedPart part8 = new OutsourcedPart(8, "Part 8", 15.00, 1, 0, 10, "ACME Inc." );
        OutsourcedPart part9 = new OutsourcedPart(9, "Part 9", 20.00, 1, 0, 10, "ACME Inc." );
        OutsourcedPart part10 = new OutsourcedPart(10, "Part 10", 25.00, 1, 0, 10, "ACME Inc." );
        
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);
        Inventory.addPart(part6);
        Inventory.addPart(part7);
        Inventory.addPart(part8);
        Inventory.addPart(part9);
        Inventory.addPart(part10);
        
        Product product1 = new Product(1, "Product 1", 20.00, 1, 1, 1);
        Product product2 = new Product(2, "Product 2", 30.00, 1, 1, 1);
        Product product3 = new Product(3, "Product 3", 40.00, 1, 1, 1);
        Product product4 = new Product(4, "Product 4", 50.00, 1, 1, 1);
        Product product5 = new Product(5, "Product 5", 60.00, 1, 1, 1);
        
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        Inventory.addProduct(product5);
       
        
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part2);
        product1.addAssociatedPart(part3);
        
        product2.addAssociatedPart(part3);
        product2.addAssociatedPart(part4);
        
        product3.addAssociatedPart(part5);
        product3.addAssociatedPart(part1);
        
        product4.addAssociatedPart(part1);
        product4.addAssociatedPart(part3);
        product4.addAssociatedPart(part5);
        
        product5.addAssociatedPart(part2);
     
        launch(args);
        
    }
        
}
