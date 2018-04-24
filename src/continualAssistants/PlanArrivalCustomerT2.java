package continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import simulation.*;
import agents.*;
import entity.Customer;

//meta! id="16"
public class PlanArrivalCustomerT2 extends Scheduler
{   
	
    private static ExponentialRNG _exp = new ExponentialRNG(Config.averageArrivalT2); // second
    
    public PlanArrivalCustomerT2(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentEnvironment", id="17", type="Start"
	public void processStart(MessageForm message)
	{
            message.setCode(Mc.newCustomer);
            hold(_exp.sample(), message);
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
                
                case Mc.newCustomer:
                processNewCustomer(message);            	
                break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"
        
        private void processNewCustomer(MessageForm message) {
        MyMessage msg = new MyMessage((MyMessage) message);
        hold(_exp.sample(), msg);

        ((MyMessage) message).setCustomer(new Customer(mySim()));
        assistantFinished(message);
    }


	@Override
	public AgentEnvironment myAgent()
	{
		return (AgentEnvironment)super.myAgent();
	}

}
