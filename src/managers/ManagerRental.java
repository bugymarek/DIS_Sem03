package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="24"
public class ManagerRental extends Manager
{
	public ManagerRental(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentService", id="94", type="Response"
	public void processServeCustomer(MessageForm message)
	{
	}

	//meta! sender="AgentBoardingCustomers", id="82", type="Response"
	public void processLoadCustomer(MessageForm message)
	{
	}

	//meta! sender="AgentAirport", id="40", type="Notice"
	public void processArrivalCustomer(MessageForm message)
	{
	}

	//meta! sender="AgentAirport", id="46", type="Request"
	public void processMovMinibusToRental(MessageForm message)
	{
	}

	//meta! sender="ProcessMovingMinibusToRental", id="72", type="Finish"
	public void processFinish(MessageForm message)
	{
	}

	//meta! sender="AgentBoardingCustomers", id="84", type="Response"
	public void processUnloadCustomer(MessageForm message)
	{
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.serveCustomer:
			processServeCustomer(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		case Mc.unloadCustomer:
			processUnloadCustomer(message);
		break;

		case Mc.loadCustomer:
			processLoadCustomer(message);
		break;

		case Mc.movMinibusToRental:
			processMovMinibusToRental(message);
		break;

		case Mc.arrivalCustomer:
			processArrivalCustomer(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentRental myAgent()
	{
		return (AgentRental)super.myAgent();
	}

}
