package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import entity.Minibus;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//meta! id="1"
public class AgentModel extends Agent {

    private List<Minibus> _minibusesList;

    public AgentModel(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        _minibusesList = new ArrayList<>(Config.MinibusesCount);
        for (int i = 0; i < Config.MinibusesCount; ++i) {
            Minibus mb = new Minibus(_mySim);
            mb.setID(i);
            _minibusesList.add(mb);
        }
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerModel(Id.managerModel, mySim(), this);
        addOwnMessage(Mc.arrivalCustomerT1);
        addOwnMessage(Mc.arrivalCustomerT2);
        addOwnMessage(Mc.arrivalCustomerRental);
        addOwnMessage(Mc.departureCustomer);
    }
    //meta! tag="end"

    public void startSimulation() {

        MyMessage msg = new MyMessage(mySim(), null, null);
        msg.setAddressee(this);
        msg.setCode(Mc.init);
        manager().notice(msg);
        
        for (int i = 0; i < _minibusesList.size(); i++) {
        MyMessage message = new MyMessage(mySim(), null, _minibusesList.get(i)); 
        message.setCode(Mc.minibusReadyForMove);
        message.setAddressee(mySim().findAgent(Id.agentAirport));
        manager().notice(message);
        }     
    }

    public List<Minibus> getMinibusesList() {
        return _minibusesList;
    }
}
