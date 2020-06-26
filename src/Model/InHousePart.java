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
public class InHousePart extends Part{
    
    //Instance Variables
    private int machineID;
    
    //Constructor

    public InHousePart(int ID, String name, double price, int stock, int min, int max, int machineID) {
        super(ID, name, price, stock, min, max);
        this.machineID = machineID;
    }
   

    //Getters and Setters
    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    
}
