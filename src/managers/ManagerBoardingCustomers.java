package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="52"
public class ManagerBoardingCustomers extends Manager
{
	public ManagerBoardingCustomers(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentRental", id="82", type="Request"
	public void processLoadCustomerAgentRental(MessageForm message)
	{
	}

	//meta! sender="AgentT2", id="57", type="Request"
	public void processLoadCustomerAgentT2(MessageForm message)
	{
	}

	//meta! sender="AgentT1", id="83", type="Request"
	public void processLoadCustomerAgentT1(MessageForm message)
	{
	}

	//meta! sender="ProcessUnloadCustomer", id="87", type="Finish"
	public void processFinishProcessUnloadCustomer(MessageForm message)
	{
	}

	//meta! sender="ProcessLoadCustomer", id="81", type="Finish"
	public void processFinishProcessLoadCustomer(MessageForm message)
	{
	}

	//meta! sender="AgentRental", id="84", type="Request"
	public void processUnloadCustomerAgentRental(MessageForm message)
	{
	}

	//meta! sender="AgentT3", id="85", type="Request"
	public void processUnloadCustomerAgentT3(MessageForm message)
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
		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.processUnloadCustomer:
				processFinishProcessUnloadCustomer(message);
			break;

			case Id.processLoadCustomer:
				processFinishProcessLoadCustomer(message);
			break;
			}
		break;

		case Mc.unloadCustomer:
			switch (message.sender().id())
			{
			case Id.agentRental:
				processUnloadCustomerAgentRental(message);
			break;

			case Id.agentT3:
				processUnloadCustomerAgentT3(message);
			break;
			}
		break;

		case Mc.loadCustomer:
			switch (message.sender().id())
			{
			case Id.agentRental:
				processLoadCustomerAgentRental(message);
			break;

			case Id.agentT2:
				processLoadCustomerAgentT2(message);
			break;

			case Id.agentT1:
				processLoadCustomerAgentT1(message);
			break;
			}
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentBoardingCustomers myAgent()
	{
		return (AgentBoardingCustomers)super.myAgent();
	}

}
