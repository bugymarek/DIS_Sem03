/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import OSPABA.Entity;
import OSPABA.Simulation;
import simulation.Config;

/**
 *
 * @author Bugy
 */
public class Operator extends Entity {
    private boolean Occupied;
    private int _id;
    private double _allWorkingTime;
    private double _startWorkingTime;
    
    public Operator(Simulation mySim, int id) {
        super(mySim);
        this.Occupied = false;
        this._id = id;
        this._allWorkingTime = 0;
        this._startWorkingTime = 0;
    }
    
    public boolean isOccupied() {
        return Occupied;
    }

    public void setOccupied(boolean occupied) {
        this.Occupied = occupied;
        // prvu hodinu zahrievam tak nepocitam ze pracuju;
        if(mySim().currentTime()<= Config.SimHour)return;
        if(occupied){
            _startWorkingTime = mySim().currentTime();
        }else{
            _allWorkingTime += (mySim().currentTime() - _startWorkingTime);
        }
    }

    public double getAllWorkingTime() {
//        if(this.Occupied){
//           return _allWorkingTime += (mySim().currentTime() - _startWorkingTime);
//        }
        return _allWorkingTime;
    }

    public int getId() {
        return _id;
    }
    
}
