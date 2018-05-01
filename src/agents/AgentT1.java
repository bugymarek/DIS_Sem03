package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.WStat;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="21"
public class AgentT1 extends Agent {

    private SimQueue< MessageForm> _customersQueue;
    private int _arrivalCustomersCount;

    public AgentT1(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        _customersQueue = new SimQueue<>(new WStat(mySim()));
        _arrivalCustomersCount = 0;
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerT1(Id.managerT1, mySim(), this);
        addOwnMessage(Mc.arrivalCustomer);
        addOwnMessage(Mc.loadCustomerDone);
        addOwnMessage(Mc.serveArrivalMinibus);
    }
    
    public SimQueue<MessageForm> getCustomersQueue() {
        return _customersQueue;
    }
    
    public MessageForm getAvailableCustomersFromQueue(int freePlaces) {
        for (int i = _customersQueue.size() - 1; i >= 0; i--) {
            if(((MyMessage)_customersQueue.get(i)).getCustomer().getPassengersCount() <= freePlaces){
                return _customersQueue.remove(i);
            }
        }
        return null;
    }

    public WStat lengthQueueWStat() {
        return _customersQueue.lengthStatistic();
    }
    //meta! tag="end"

    public int getArrivalCustomersCount() {
        return _arrivalCustomersCount;
    }
    
    public void incrementCustomersCount() {
        _arrivalCustomersCount++;
    }
    
}
