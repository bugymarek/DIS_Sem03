package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="89"
public class AgentService extends Agent
{
	public AgentService(int id, Simulation mySim, Agent parent)
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
		new ManagerService(Id.managerService, mySim(), this);
		new ProcessServeCustomer(Id.processServeCustomer, mySim(), this);
		addOwnMessage(Mc.serveCustomer);
	}
	//meta! tag="end"
}
