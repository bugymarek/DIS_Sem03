package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="35"
public class ManagerAirport extends Manager
{
	public ManagerAirport(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentModel", id="25", type="Notice"
	public void processArrivalCustomer(MessageForm message)
	{
	}

	//meta! sender="AgentRental", id="46", type="Response"
	public void processMovMinibusToRental(MessageForm message)
	{
	}

	//meta! sender="AgentT3", id="45", type="Response"
	public void processMovMinibusToT3(MessageForm message)
	{
	}

	//meta! sender="AgentT2", id="43", type="Response"
	public void processMovMinibusToT2(MessageForm message)
	{
	}

	//meta! sender="AgentT3", id="32", type="Notice"
	public void processDepartureCustomerAgentT3(MessageForm message)
	{
	}

	//meta! sender="AgentRental", id="33", type="Notice"
	public void processDepartureCustomerAgentRental(MessageForm message)
	{
	}

	//meta! sender="AgentT1", id="44", type="Response"
	public void processMovMinibusToT1(MessageForm message)
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
		case Mc.movMinibusToT2:
			processMovMinibusToT2(message);
		break;

		case Mc.departureCustomer:
			switch (message.sender().id())
			{
			case Id.agentT3:
				processDepartureCustomerAgentT3(message);
			break;

			case Id.agentRental:
				processDepartureCustomerAgentRental(message);
			break;
			}
		break;

		case Mc.movMinibusToT3:
			processMovMinibusToT3(message);
		break;

		case Mc.movMinibusToT1:
			processMovMinibusToT1(message);
		break;

		case Mc.arrivalCustomer:
			processArrivalCustomer(message);
		break;

		case Mc.movMinibusToRental:
			processMovMinibusToRental(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentAirport myAgent()
	{
		return (AgentAirport)super.myAgent();
	}

}
