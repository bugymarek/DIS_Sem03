package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="74"
public class ProcessMovingMinibusFromT3ToT1 extends Process
{
	public ProcessMovingMinibusFromT3ToT1(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentT3", id="75", type="Start"
	public void processStart(MessageForm message)
	{
            message.setCode(Mc.movingMinibusDone);
            hold(Config.LengthT3ToT1,message);
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
