package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="35"
public class AgentAirport extends Agent
{
	public AgentAirport(int id, Simulation mySim, Agent parent)
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
		new ManagerAirport(Id.managerAirport, mySim(), this);
		addOwnMessage(Mc.arrivalCustomer);
		addOwnMessage(Mc.movMinibusToRental);
		addOwnMessage(Mc.movMinibusToT3);
		addOwnMessage(Mc.movMinibusToT2);
		addOwnMessage(Mc.departureCustomer);
		addOwnMessage(Mc.movMinibusToT1);
	}
	//meta! tag="end"
}
