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

    public AgentT1(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        _customersQueue = new SimQueue<>(new WStat(mySim()));
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerT1(Id.managerT1, mySim(), this);
        addOwnMessage(Mc.arrivalCustomer);
        addOwnMessage(Mc.loadCustomer);
        addOwnMessage(Mc.arrivalMinibus);
    }
    
    public SimQueue<MessageForm> getCustomersQueue() {
        return _customersQueue;
    }

    public WStat lengthQueueWStat() {
        return _customersQueue.lengthStatistic();
    }
    //meta! tag="end"
}
