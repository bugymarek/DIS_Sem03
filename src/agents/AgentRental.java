package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.WStat;
import simulation.*;
import managers.*;
import continualAssistants.*;
import entity.Operator;
import java.util.ArrayList;

//meta! id="24"
public class AgentRental extends Agent {

    private SimQueue< MessageForm> _customersUnloadQueue;
    private SimQueue< MessageForm> _customersLoadQueue;
    private ArrayList<Operator> _operatorsList;
    private int _arrivalCustomersCount;

    public AgentRental(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();      
        _customersUnloadQueue = new SimQueue<>(new WStat(mySim()));
        _customersLoadQueue = new SimQueue<>(new WStat(mySim()));
        _arrivalCustomersCount = 0;
        initOperatorsList();
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerRental(Id.managerRental, mySim(), this);
        new ProcessServeCustomer(Id.processServeCustomer, mySim(), this);
        initOperatorsList();
        addOwnMessage(Mc.serveCustomer);
        addOwnMessage(Mc.serveCustomerDone);
        addOwnMessage(Mc.loadCustomerDone);
        addOwnMessage(Mc.arrivalCustomer);
        addOwnMessage(Mc.serveArrivalMinibus);
        addOwnMessage(Mc.unloadCustomerDone);
    }
    //meta! tag="end"

    public SimQueue<MessageForm> getCustomersUnloadQueue() {
        return _customersUnloadQueue;
    }

    public SimQueue<MessageForm> getCustomersLoadQueue() {
        return _customersLoadQueue;
    }

    public WStat lengthUnloadQueueWStat() {
        return _customersUnloadQueue.lengthStatistic();
    }
    
    private void initOperatorsList(){
        _operatorsList = new ArrayList<>();
        for (int i = 0; i < Config.OperatorsCount; i++) {
            _operatorsList.add(new Operator(mySim(), i));
        }
    }
    
    private void setAllOperatorsNotWork(){
        for (Operator operator : _operatorsList) {
            operator.setOccupied(false);
        }
    }
    
    public double getAllWorkingTime(){
        double allWorkingTime = 0; 
        for (Operator operator : _operatorsList) {
            allWorkingTime += operator.getAllWorkingTime();
        }
        return allWorkingTime;
    }
    
    public Operator getFreeOperator() {
        for (Operator o : this._operatorsList) {
            if (!o.isOccupied()) {
                return o;
            }
        }
        return null;
    }

    public int getFreeOperatorsCount() {
        int count = 0;
        for (Operator o : this._operatorsList) {
            if (!o.isOccupied()) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Operator> getOperatorsList() {
        return _operatorsList;
    }
    
    public int getArrivalCustomersCount() {
        return _arrivalCustomersCount;
    }
    
    public void incrementCustomersCount() {
        _arrivalCustomersCount++;
    }
}
