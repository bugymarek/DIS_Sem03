package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="21"
public class AgentT1 extends Agent
{
	public AgentT1(int id, Simulation mySim, Agent parent)
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
		new ManagerT1(Id.managerT1, mySim(), this);
		new ProcessMovingMinibusToT1(Id.processMovingMinibusToT1, mySim(), this);
		addOwnMessage(Mc.arrivalCustomer);
		addOwnMessage(Mc.loadCustomer);
		addOwnMessage(Mc.movMinibusToT1);
	}
	//meta! tag="end"
}
