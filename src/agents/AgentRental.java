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
    private ArrayList<Operator> _operatorsList;

    public AgentRental(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();      
        _customersUnloadQueue = new SimQueue<>(new WStat(mySim()));
        initOperatorsList();
        setAllOperatorsNotWork();
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

    public WStat lengthUnloadQueueWStat() {
        return _customersUnloadQueue.lengthStatistic();
    }
    
    private void initOperatorsList(){
        _operatorsList = new ArrayList<>();
        for (int i = 0; i < Config.OperatorsCount; i++) {
            _operatorsList.add(new Operator(mySim()));
        }
    }
    
    private void setAllOperatorsNotWork(){
        for (Operator operator : _operatorsList) {
            operator.setOccupied(false);
        }
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
}
