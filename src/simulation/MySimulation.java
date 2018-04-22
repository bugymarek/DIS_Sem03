package simulation;

import OSPABA.*;
import agents.*;

public class MySimulation extends Simulation
{
	public MySimulation()
	{
		init();
	}

	@Override
	public void prepareSimulation()
	{
		super.prepareSimulation();
		// Create global statistcis
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Reset entities, queues, local statistics, etc...
                agentModel().startSimulation();
	}

	@Override
	public void replicationFinished()
	{
		// Collect local statistics into global, update UI, etc...
		super.replicationFinished();
	}

	@Override
	public void simulationFinished()
	{
		// Dysplay simulation results
		super.simulationFinished();
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		setAgentModel(new AgentModel(Id.agentModel, this, null) {});
		setAgentAirport(new AgentAirport(Id.agentAirport, this, agentModel()));
		setAgentEnvironment(new AgentEnvironment(Id.agentEnvironment, this, agentModel()));
		setAgentBoardingCustomers(new AgentBoardingCustomers(Id.agentBoardingCustomers, this, agentAirport()));
		setAgentT1(new AgentT1(Id.agentT1, this, agentAirport()));
		setAgentT2(new AgentT2(Id.agentT2, this, agentAirport()));
		setAgentT3(new AgentT3(Id.agentT3, this, agentAirport()));
		setAgentRental(new AgentRental(Id.agentRental, this, agentAirport()));
		setAgentService(new AgentService(Id.agentService, this, agentRental()));
	}

	private AgentModel _agentModel;

public AgentModel agentModel()
	{ return _agentModel; }

	public void setAgentModel(AgentModel agentModel)
	{_agentModel = agentModel; }

	private AgentAirport _agentAirport;

public AgentAirport agentAirport()
	{ return _agentAirport; }

	public void setAgentAirport(AgentAirport agentAirport)
	{_agentAirport = agentAirport; }

	private AgentEnvironment _agentEnvironment;

public AgentEnvironment agentEnvironment()
	{ return _agentEnvironment; }

	public void setAgentEnvironment(AgentEnvironment agentEnvironment)
	{_agentEnvironment = agentEnvironment; }

	private AgentBoardingCustomers _agentBoardingCustomers;

public AgentBoardingCustomers agentBoardingCustomers()
	{ return _agentBoardingCustomers; }

	public void setAgentBoardingCustomers(AgentBoardingCustomers agentBoardingCustomers)
	{_agentBoardingCustomers = agentBoardingCustomers; }

	private AgentT1 _agentT1;

public AgentT1 agentT1()
	{ return _agentT1; }

	public void setAgentT1(AgentT1 agentT1)
	{_agentT1 = agentT1; }

	private AgentT2 _agentT2;

public AgentT2 agentT2()
	{ return _agentT2; }

	public void setAgentT2(AgentT2 agentT2)
	{_agentT2 = agentT2; }

	private AgentT3 _agentT3;

public AgentT3 agentT3()
	{ return _agentT3; }

	public void setAgentT3(AgentT3 agentT3)
	{_agentT3 = agentT3; }

	private AgentRental _agentRental;

public AgentRental agentRental()
	{ return _agentRental; }

	public void setAgentRental(AgentRental agentRental)
	{_agentRental = agentRental; }

	private AgentService _agentService;

public AgentService agentService()
	{ return _agentService; }

	public void setAgentService(AgentService agentService)
	{_agentService = agentService; }
	//meta! tag="end"
}
