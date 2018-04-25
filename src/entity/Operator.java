/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import OSPABA.Entity;
import OSPABA.Simulation;

/**
 *
 * @author Bugy
 */
public class Operator extends Entity {
    private boolean Occupied;
    
    public Operator(Simulation mySim) {
        super(mySim);
        this.Occupied = false;
    }
    
    public boolean isOccupied() {
        return Occupied;
    }

    public void setOccupied(boolean Occupied) {
        this.Occupied = Occupied;
    }
}
