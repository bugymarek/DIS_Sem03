package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="22"
public class ManagerT2 extends Manager
{
	public ManagerT2(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentAirport", id="36", type="Notice"
	public void processArrivalCustomer(MessageForm message)
	{
	}

	//meta! sender="AgentBoardingCustomers", id="57", type="Response"
	public void processLoadCustomer(MessageForm message)
	{
	}

	//meta! sender="ProcessMovingMinibusToT2", id="78", type="Finish"
	public void processFinish(MessageForm message)
	{
	}

	//meta! sender="AgentAirport", id="43", type="Request"
	public void processMovMinibusToT2(MessageForm message)
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

		case Mc.finish:
			processFinish(message);
		break;

		case Mc.arrivalCustomer:
			processArrivalCustomer(message);
		break;

		case Mc.loadCustomer:
			processLoadCustomer(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentT2 myAgent()
	{
		return (AgentT2)super.myAgent();
	}

}
