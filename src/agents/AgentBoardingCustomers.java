package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="52"
public class AgentBoardingCustomers extends Agent
{
	public AgentBoardingCustomers(int id, Simulation mySim, Agent parent)
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
		new ManagerBoardingCustomers(Id.managerBoardingCustomers, mySim(), this);
		new ProcessLoadCustomer(Id.processLoadCustomer, mySim(), this);
		new ProcessUnloadCustomer(Id.processUnloadCustomer, mySim(), this);
		addOwnMessage(Mc.loadCustomer);
		addOwnMessage(Mc.unloadCustomer);
	}
	//meta! tag="end"
}
