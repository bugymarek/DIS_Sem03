package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="1"
public class AgentModel extends Agent
{
	public AgentModel(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerModel(Id.managerModel, mySim(), this);
		addOwnMessage(Mc.arrivalCustomerT1);
                addOwnMessage(Mc.arrivalCustomerT2);
                addOwnMessage(Mc.arrivalCustomerRental);
		addOwnMessage(Mc.departureCustomer);
		addOwnMessage(Mc.init);
	}
	//meta! tag="end"
        
        public void startSimulation() {

        MyMessage msg = new MyMessage(mySim(), null);
        msg.setAddressee(this);
        msg.setCode(Mc.init);
        manager().notice(msg);
    }
}
