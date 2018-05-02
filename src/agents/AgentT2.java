package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.WStat;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="22"
public class AgentT2 extends Agent {

    private SimQueue<MessageForm> _customersQueue;
    private int _arrivalCustomersCount;
    private int _departureCustomersCount;
    private SimQueue< Integer> _customersStatQueue;

    public AgentT2(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        _customersQueue = new SimQueue<>(new WStat(mySim()));
        _customersStatQueue = new SimQueue<>(new WStat(mySim()));
        _arrivalCustomersCount = 0;
        _departureCustomersCount = 0;
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerT2(Id.managerT2, mySim(), this);
        addOwnMessage(Mc.arrivalCustomer);
        addOwnMessage(Mc.loadCustomerDone);
        addOwnMessage(Mc.serveArrivalMinibus);
    }
    //meta! tag="end"
    
    public SimQueue<MessageForm> getCustomersQueue() {
        return _customersQueue;
    }

    public WStat lengthQueueWStatInteger() {
        return _customersStatQueue.lengthStatistic();
    }
    
    public SimQueue<Integer> getCustomersStatQueue() {
        return _customersStatQueue;
    }
    
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
        _departureCustomersCount+= value;
    }
    
    public void enqueuQueue(MessageForm message) {
        _customersQueue.enqueue(message);
        int passengersCount = ((MyMessage)message).getCustomer().getPassengersCount();
        for (int i = 0; i < passengersCount; i++) {
            _customersStatQueue.enqueue(1);
        }
    }
    
    public void getFromStatQueue(int passengersCount) {
        for (int i = 0; i < passengersCount; i++) {
            _customersStatQueue.dequeue();
        }
    }
    
    public MessageForm getAvailableCustomersFromQueue(int freePlaces) {
        for (int i = _customersQueue.size() - 1; i >= 0; i--) {
            int passengersCount = ((MyMessage)_customersQueue.get(i)).getCustomer().getPassengersCount();
            if(passengersCount <= freePlaces){
                getFromStatQueue(passengersCount);
                return _customersQueue.remove(i);
            }
        }
        return null;
    }
}
