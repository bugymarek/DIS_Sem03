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
                new ProcessMovingMinibusToT3(Id.processMovingMinibusToT3, mySim(), this);
		new ProcessMovingMinibusToRental(Id.processMovingMinibusToRental, mySim(), this);
                new ProcessMovingMinibusFromT3ToT1(Id.processMovingMinibusFromT3ToT1, mySim(), this);
		new ProcessMovingMinibusToT2(Id.processMovingMinibusToT2, mySim(), this);
		new ProcessMovingMinibusToT1(Id.processMovingMinibusToT1, mySim(), this);
		addOwnMessage(Mc.arrivalCustomerT1);
                addOwnMessage(Mc.arrivalCustomerT2);
                addOwnMessage(Mc.arrivalCustomerRental);
                addOwnMessage(Mc.minibusReadyForMove);             
                addOwnMessage(Mc.serveArrivalMinibus); 
                addOwnMessage(Mc.movingMinibusDone);
		addOwnMessage(Mc.departureCustomer);
                addOwnMessage(Mc.loadCustomer);
                addOwnMessage(Mc.unloadCustomer);
	}
	//meta! tag="end"
}
