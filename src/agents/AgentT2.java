package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="22"
public class AgentT2 extends Agent
{
	public AgentT2(int id, Simulation mySim, Agent parent)
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
		new ManagerT2(Id.managerT2, mySim(), this);
		new ProcessMovingMinibusToT2(Id.processMovingMinibusToT2, mySim(), this);
		addOwnMessage(Mc.arrivalCustomer);
		addOwnMessage(Mc.loadCustomer);
		addOwnMessage(Mc.movMinibusToT2);
	}
	//meta! tag="end"
}
