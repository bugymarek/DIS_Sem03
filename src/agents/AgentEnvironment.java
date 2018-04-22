package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="3"
public class AgentEnvironment extends Agent
{
	public AgentEnvironment(int id, Simulation mySim, Agent parent)
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
		new ManagerEnvironment(Id.managerEnvironment, mySim(), this);
		new PlanArrivalCustomerT2(Id.planArrivalCustomerT2, mySim(), this);
		new PlanArrivalCustomerRental(Id.planArrivalCustomerRental, mySim(), this);
		new PlanArrivalCustomerT1(Id.planArrivalCustomerT1, mySim(), this);
		addOwnMessage(Mc.init);
		addOwnMessage(Mc.departureCustomer);
	}
	//meta! tag="end"
}
