package agents;

import OSPABA.*;
import OSPStat.Stat;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="3"
public class AgentEnvironment extends Agent
{
        private Stat _statWaitingTime;
        
	public AgentEnvironment(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
                _statWaitingTime = new Stat();
		// Setup component for the next replication
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerEnvironment(Id.managerEnvironment, mySim(), this);
		new PlanArrivalCustomerT2(Id.planArrivalCustomerT2, mySim(), this);
		new PlanArrivalCustomerRental(Id.planArrivalCustomerRental, mySim(), this);
		new PlanArrivalCustomerT1(Id.planArrivalCustomerT1, mySim(), this);
		addOwnMessage(Mc.init);
                addOwnMessage(Mc.newCustomer);
		addOwnMessage(Mc.departureCustomer);
	}
	//meta! tag="end"
        
       public Stat getStatWaitingTime() {
        return _statWaitingTime;
    } 
}
