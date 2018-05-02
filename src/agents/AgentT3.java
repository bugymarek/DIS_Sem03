package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="23"
public class AgentT3 extends Agent {

    private int _arrivalCustomersCount;
    private int _departureCustomersCount;

    public AgentT3(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
        _arrivalCustomersCount = 0;
        _departureCustomersCount = 0;
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerT3(Id.managerT3, mySim(), this);
        addOwnMessage(Mc.serveArrivalMinibus);
        addOwnMessage(Mc.unloadCustomerDone);
    }
    //meta! tag="end"

    public int getArrivalCustomersCount() {
        return _arrivalCustomersCount;
    }

    public void incrementCustomersCount(int value) {
        _arrivalCustomersCount += value;
    }
    
    public int getDepartureCustomersCount() {
        return _departureCustomersCount;
    }
    
    public void incrementDepartureCustomersCount(int value) {
        _departureCustomersCount += value;
    }
    
}
