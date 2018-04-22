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

    public AgentT2(int id, Simulation mySim, Agent parent) {
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
        new ManagerT2(Id.managerT2, mySim(), this);
        new ProcessMovingMinibusToT2(Id.processMovingMinibusToT2, mySim(), this);
        addOwnMessage(Mc.arrivalCustomer);
        addOwnMessage(Mc.loadCustomer);
        addOwnMessage(Mc.arrivalMinibus);
    }
    //meta! tag="end"
    
    public SimQueue<MessageForm> getCustomersQueue() {
        return _customersQueue;
    }

    public WStat lengthQueueWStat() {
        return _customersQueue.lengthStatistic();
    }
}
