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
import simulation.Config;

/**
 *
 * @author Bugy
 */
public class Minibus extends Entity{
    private SimQueue<Customer> _customersQueue; 
    private SimQueue< Integer> _customersStatQueue;
    private int _ID;
    private String _position;
    private int _passengersCount;
    private int _mileage;
    
    public Minibus(Simulation mySim) {
        super(mySim);
        _customersQueue = new SimQueue<>(new WStat(mySim()));
        _customersStatQueue = new SimQueue<>(new WStat(mySim()));
        _passengersCount = 0;
        _mileage = 0;
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
    
    public SimQueue<Integer> getCustomersStatQueue() {
        return _customersStatQueue;
    }
    
    public boolean isPlaceInBus(){
        return _passengersCount < Config.CapaityOfMinibus;
    }
    
    public int getFreePlaces(){
        return Config.CapaityOfMinibus - _passengersCount;
    }
    
    public int getPassengersCount(){
        return _passengersCount;
    }
    
    public boolean isEmpty(){
        return _customersQueue.isEmpty();
    }
    
    public Customer getCustomerFromBus(){
        Customer cus = _customersQueue.dequeue();
        _passengersCount -= cus.getPassengersCount();
        for (int i = 0; i < cus.getPassengersCount(); i++) {
            _customersStatQueue.dequeue();
        }
        return cus;
    }
    
    public void addCustomerToBus(Customer cus){
        _customersQueue.enqueue(cus);
        _passengersCount += cus.getPassengersCount();
        for (int i = 0; i < cus.getPassengersCount(); i++) {
            _customersStatQueue.enqueue(1);
        }  
    }

    public WStat lengthQueueWStat() {
        return _customersQueue.lengthStatistic();
    }
    public WStat lengthQueueWStatInteger() {
        return _customersStatQueue.lengthStatistic();
    }
    
    public void addKm(int kmCount){
        _mileage += kmCount;
    }

    public int getMileage() {
        return _mileage;
    }
}
