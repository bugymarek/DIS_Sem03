package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="80"
public class ProcessLoadCustomer extends Process
{
	public ProcessLoadCustomer(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentBoardingCustomers", id="81", type="Start"
	public void processStart(MessageForm message)
	{
            message.setCode(Mc.loadCustomerDone);
            hold(Config.BoardingLowerLimit,message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.start:                     
			processStart(message);
		break;
                
                case Mc.loadCustomerDone:
			assistantFinished(message);

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
