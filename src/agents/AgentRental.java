package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="24"
public class AgentRental extends Agent
{
	public AgentRental(int id, Simulation mySim, Agent parent)
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
		new ManagerRental(Id.managerRental, mySim(), this);
		new ProcessMovingMinibusToRental(Id.processMovingMinibusToRental, mySim(), this);
		addOwnMessage(Mc.serveCustomer);
		addOwnMessage(Mc.loadCustomer);
		addOwnMessage(Mc.arrivalCustomer);
		addOwnMessage(Mc.movMinibusToRental);
		addOwnMessage(Mc.unloadCustomer);
	}
	//meta! tag="end"
}
