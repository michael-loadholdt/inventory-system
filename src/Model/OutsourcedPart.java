/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author micha
 */
public class OutsourcedPart extends Part{
    
    //Instance Variables
    private String companyName;

    //Constructor
    public OutsourcedPart(int ID, String name, double price, int stock, int min, int max, String companyName){
        super(ID, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    //Getters and Setters

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    
}
