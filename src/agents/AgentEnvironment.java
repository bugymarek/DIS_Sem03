package agents;

import OSPABA.*;
import OSPStat.Stat;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="3"
public class AgentEnvironment extends Agent {

    private Stat _statWaitingTimeForAllCustomers;
    private Stat _statWaitingTimeReturnCarCustomers;
    private Stat _statWaitingTimeRentCarCustomers;
    private int _generatedCustomerCount;
    private int _departuresCustomersCount;

    public AgentEnvironment(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        _statWaitingTimeForAllCustomers = new Stat();
        _statWaitingTimeRentCarCustomers = new Stat();
        _statWaitingTimeReturnCarCustomers = new Stat();
        _generatedCustomerCount = 0;
        _departuresCustomersCount = 0;
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerEnvironment(Id.managerEnvironment, mySim(), this);
        new PlanArrivalCustomerT2(Id.planArrivalCustomerT2, mySim(), this);
        new PlanArrivalCustomerRental(Id.planArrivalCustomerRental, mySim(), this);
        new PlanArrivalCustomerT1(Id.planArrivalCustomerT1, mySim(), this);
        addOwnMessage(Mc.init);
        addOwnMessage(Mc.newCustomer);
        addOwnMessage(Mc.departureCustomer);
    }
    //meta! tag="end"

    public Stat getStatWaitingTimeForAllCustomers() {
        return _statWaitingTimeForAllCustomers;
    }

    public Stat getStatWaitingTimeReturnCarCustomers() {
        return _statWaitingTimeReturnCarCustomers;
    }

    public Stat getStatWaitingTimeRentCarCustomers() {
        return _statWaitingTimeRentCarCustomers;
    }
    
    
    public int getGeneretedCustomerCount() {
        return _generatedCustomerCount;
    }
    
    public void incrementGeneratedCustomersCount(int value){
        _generatedCustomerCount += value;
    }

    public int getDeparturesCustomersCount() {
        return _departuresCustomersCount;
    }
    
    public void incrementDeparturesCustomersCount(int value){
        _departuresCustomersCount +=value;
    }
}
