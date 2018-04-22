/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import OSPABA.Entity;
import OSPABA.MessageForm;
import OSPABA.Simulation;
import OSPDataStruct.SimQueue;
import OSPStat.WStat;

/**
 *
 * @author Bugy
 */
public class Minibus extends Entity{
    private SimQueue<Customer> _customersQueue; 
    private int _ID;
    private String _position;
    
    public Minibus(Simulation mySim) {
        super(mySim);
        _customersQueue = new SimQueue<>(new WStat(mySim()));
    }
    
    public String getPosition() {
        return _position;
    }

    public int getID() {
        return _ID;
    }

    public void setID(int _ID) {
        this._ID = _ID;
    }

    public void setPosition(String Position) {
        this._position = Position;
    }
    
    public SimQueue<Customer> getCustomersQueue() {
        return _customersQueue;
    }
    
    public boolean isPlaceInBus(){
        return _customersQueue.size()<12;
    }
    
    public int getSize(){
        return _customersQueue.size();
    }
    
    public boolean isEmpty(){
        return _customersQueue.isEmpty();
    }
    
    public Customer getCustomerFromBus(){
        return _customersQueue.dequeue();
    }
    
    public void addCustomerToBus(Customer cus){
        _customersQueue.enqueue(cus);
    }

    public WStat lengthQueueWStat() {
        return _customersQueue.lengthStatistic();
    }
}
