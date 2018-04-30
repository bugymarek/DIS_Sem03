package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="3"
public class ManagerEnvironment extends Manager
{
	public ManagerEnvironment(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentModel", id="20", type="Notice"
	public void processInit(MessageForm message)
	{
            message.setAddressee(myAgent().findAssistant(Id.planArrivalCustomerT1));         
            startContinualAssistant(message);    
            
            MyMessage msg = new MyMessage(mySim(), null, null);
            msg.setAddressee(myAgent().findAssistant(Id.planArrivalCustomerT2));         
            startContinualAssistant(msg);
            
            MyMessage msg2 = new MyMessage(mySim(), null, null);
            msg2.setAddressee(myAgent().findAssistant(Id.planArrivalCustomerRental));         
            startContinualAssistant(msg2);
	}

	//meta! sender="PlanArrivalCustomerRental", id="19", type="Finish"
	public void processFinishPlanArrivalCustomerRental(MessageForm message)
	{
            myAgent().incrementGeneratedCustomersCount();
            message.setCode(Mc.arrivalCustomerRental);
            message.setAddressee(myAgent().findAssistant(Id.agentModel));
            notice(message);
	}

	//meta! sender="PlanArrivalCustomerT2", id="17", type="Finish"
	public void processFinishPlanArrivalCustomerT2(MessageForm message)
	{
            myAgent().incrementGeneratedCustomersCount();
            message.setCode(Mc.arrivalCustomerT2);
            message.setAddressee(myAgent().findAssistant(Id.agentModel));
            notice(message);
	}

	//meta! sender="PlanArrivalCustomerT1", id="13", type="Finish"
	public void processFinishPlanArrivalCustomerT1(MessageForm message)
	{
            myAgent().incrementGeneratedCustomersCount();
            message.setCode(Mc.arrivalCustomerT1);
            message.setAddressee(myAgent().findAssistant(Id.agentModel));
            notice(message);
	}

	//meta! sender="AgentModel", id="10", type="Notice"
	public void processDepartureCustomer(MessageForm message)
	{
            myAgent().getStatWaitingTime().addSample(((MyMessage) message).getCustomer().getAllWaitingTime());
            myAgent().incrementDeparturesCustomersCount();
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
		case Mc.init:
			processInit(message);
		break;

		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.planArrivalCustomerRental:
				processFinishPlanArrivalCustomerRental(message);
			break;

			case Id.planArrivalCustomerT2:
				processFinishPlanArrivalCustomerT2(message);
			break;

			case Id.planArrivalCustomerT1:
				processFinishPlanArrivalCustomerT1(message);
			break;
			}
		break;

		case Mc.departureCustomer:
			processDepartureCustomer(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentEnvironment myAgent()
	{
		return (AgentEnvironment)super.myAgent();
	}

}
