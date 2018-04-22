package simulation;

import OSPABA.*;
import entity.Customer;
import entity.Minibus;

public class MyMessage extends MessageForm {

    private Customer _customer;
    private double _countDepartureCustomers;
    private Minibus _minibus;

    /**
     * Kopirovaci konstruktor
     */
    public MyMessage(MyMessage original) {
        super(original);
        _customer = original._customer;
        _minibus = original._minibus;
        _countDepartureCustomers = original._countDepartureCustomers;
    }
    
    public MyMessage(Simulation mySim, Customer customer, Minibus minibus) {
        super(mySim);
        _customer = customer;
        _minibus = minibus;
        _countDepartureCustomers = 0;
    }

    @Override
    public MessageForm createCopy() {
        return new MyMessage(this);
    }

    @Override
    protected void copy(MessageForm message) {
        super.copy(message);
        MyMessage original = (MyMessage) message;
        // Copy attributes
    }

    public Customer getCustomer() {
        return _customer;
    }

    public double getCountDepartureCustomers() {
        return _countDepartureCustomers;
    }

    public void setCustomer(Customer _customer) {
        this._customer = _customer;
    }

    public void setCountDepartureCustomers(double _countDepartureCustomers) {
        this._countDepartureCustomers = _countDepartureCustomers;
    }

    public void setMinibus(Minibus _minibus) {
        this._minibus = _minibus;
    }

    public Minibus getMinibus() {
        return _minibus;
    }  
}
