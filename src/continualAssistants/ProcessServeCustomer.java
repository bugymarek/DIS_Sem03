package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="97"
public class ProcessServeCustomer extends Process
{
	public ProcessServeCustomer(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentService", id="98", type="Start"
	public void processStart(MessageForm message)
	{
            System.out.println("AgentRental zacal som obsluhovat zakaznika: " + ((MyMessage) message).getCustomer().getTerminalAndID() + " pocet free pracovnikov: " + myAgent().getFreeOperatorsCount());
            message.setCode(Mc.serveCustomerDone);
            hold(Config.OperatingLowerLimit,message);
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
                
                case Mc.serveCustomerDone:
			assistantFinished(message);

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
