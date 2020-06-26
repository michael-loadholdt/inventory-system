/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author micha
 */
public class Inventory {
    
    //Instance Variables
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> lookupPart = FXCollections.observableArrayList();
    private static ObservableList<Product> lookupProduct = FXCollections.observableArrayList();

   
    
    //Methods
    public static void addPart(Part newPart){
        allParts.add(newPart);
        
    }
    
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    public static Part lookupPart (int partID){
        
        for(Part part : allParts){
            
            if(partID == part.getID()){
                
               return allParts.get(partID);
                
            }
        }
    
        return null;
    }
    
    public static Product lookupProduct (int productID){
        
        for(Product product : allProducts){
            
            if(productID == product.getID()){
                
               return allProducts.get(productID);
                
            }
        }
        
        return null;
    
    }
    
    public static ObservableList<Part> getLookupPart() {
        return lookupPart;
        
    }
    
    public static ObservableList<Product> getLookupProduct() {
        return lookupProduct;
    }
    
    public static ObservableList<Part> lookupPart(String partName){
       
        if (!(getLookupPart().isEmpty())){
            lookupPart.clear();
            
        }
        
        for(Part part : allParts){
            if(part.getName().contains(partName)){
                lookupPart.add(part);
            }
        }
        if(lookupPart.isEmpty()){
            return allParts;
        }
        else{
            return lookupPart;
        }
    }
    
     public static ObservableList<Product> lookupProduct(String productName){
        
        if (!(getLookupProduct().isEmpty())){
            lookupProduct.clear();
            
        }
        
         for(Product product : allProducts){
            if(product.getName().contains(productName)){
                lookupProduct.add(product);
            }
        }
        if(lookupProduct.isEmpty()){
            return allProducts;
        }
        else{
            return lookupProduct;
        }
     }
     
     public static void deletePart(int index, Part selectedPart){
         
        allParts.remove(selectedPart);
         
     }
     
     public static void deleteProduct(Product selectedProduct){

        allProducts.remove(selectedProduct);
         
     }
     
     public static ObservableList<Part> getAllParts(){
         return allParts;
     }
     
     public static ObservableList<Product> getAllProducts(){
         return allProducts;
     }
}
