package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="71"
public class ProcessMovingMinibusToRental extends Process
{
	public ProcessMovingMinibusToRental(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentRental", id="72", type="Start"
	public void processStart(MessageForm message)
	{
            message.setCode(Mc.movingMinibusDone);
            hold(Config.TimeLengthT2ToRental,message);
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
                
                case Mc.movingMinibusDone:
			assistantFinished(message);

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
