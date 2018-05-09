package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
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
    private int _returnCarCustomersCount;
    private int _rentCarCustomersCount;
    private SimQueue< Integer> _customersStatLoadQueue;
    private Stat _occupancyWorkingTime;

    public AgentRental(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();      
        _customersUnloadQueue = new SimQueue<>(new WStat(mySim()));
        _customersLoadQueue = new SimQueue<>(new WStat(mySim()));
        _customersStatLoadQueue = new SimQueue<>(new WStat(mySim()));
        _occupancyWorkingTime = new Stat();
        _arrivalCustomersCount = 0;
        _returnCarCustomersCount =0;
        _rentCarCustomersCount = 0;
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
    
    public MessageForm getAvailableCustomersFromQueue(int freePlaces) {
        for (int i = _customersLoadQueue.size() - 1; i >= 0; i--) {
            int passengersCount = ((MyMessage)_customersLoadQueue.get(i)).getCustomer().getPassengersCount();
            if(passengersCount <= freePlaces){
                getFromStatLoadQueue(passengersCount);
                return _customersLoadQueue.remove(i);
            }
        }
        return null;
    }
    
    public WStat lengthLoadQueueWStatInteger() {
        return _customersStatLoadQueue.lengthStatistic();
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
    
    public Stat getOccupancyWorkingTime(){
        for (Operator operator : _operatorsList) {
            _occupancyWorkingTime.addSample((operator.getAllWorkingTime()* 100.0) / mySim().currentTime());
        }
        return _occupancyWorkingTime;
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
    
    public void enqueuLoadQueue(MessageForm message) {
        _customersLoadQueue.enqueue(message);
        int passengersCount = ((MyMessage)message).getCustomer().getPassengersCount();
        for (int i = 0; i < passengersCount; i++) {
            _customersStatLoadQueue.enqueue(1);
        }
    }
    
    public void getFromStatLoadQueue(int passengersCount) {
        for (int i = 0; i < passengersCount; i++) {
            _customersStatLoadQueue.dequeue();
        }
    }

    public SimQueue<Integer> getCustomersStatLoadQueue() {
        return _customersStatLoadQueue;
    }

    public ArrayList<Operator> getOperatorsList() {
        return _operatorsList;
    }
    
    public int getArrivalCustomersCount() {
        return _arrivalCustomersCount;
    }

    public int getReturnCarCustomersCount() {
        return _returnCarCustomersCount;
    }

    public int getRentCarCustomersCount() {
        return _rentCarCustomersCount;
    }
    
    public void incrementCustomersCount(int value) {
        _arrivalCustomersCount += value;
    }
    
    public void incrementReturnCustomersCount(int value) {
        _returnCarCustomersCount += value;
    }
    
    public void incrementRentCustomersCount(int value) {
        _rentCarCustomersCount += value;
    }
}
