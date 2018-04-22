package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="21"
public class ManagerT1 extends Manager {

    public ManagerT1(int id, Simulation mySim, Agent myAgent) {
        super(id, mySim, myAgent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication

        if (petriNet() != null) {
            petriNet().clear();
        }
    }

    //meta! sender="AgentAirport", id="39", type="Notice"
    public void processArrivalCustomer(MessageForm message) {
        myAgent().getCustomersQueue().enqueue(message);
        System.out.println("AgentT1 Arrival T1: " + ((MyMessage) message).getCustomer().getArrivalTimeToSystem() + "front length: " + myAgent().getCustomersQueue().size());

    }

    //meta! sender="AgentBoardingCustomers", id="83", type="Response"
    public void processLoadCustomer(MessageForm message) {
    }

    //meta! sender="ProcessMovingMinibusToT1", id="69", type="Finish"
    public void processFinish(MessageForm message) {
    }

    //meta! sender="AgentAirport", id="44", type="Request"
    public void processMovMinibusToT1(MessageForm message) {
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        switch (message.code()) {
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    public void init() {
    }

    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.movMinibusToT1:
                processMovMinibusToT1(message);
                break;

            case Mc.arrivalCustomer:
                processArrivalCustomer(message);
                break;

            case Mc.finish:
                processFinish(message);
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
    public AgentT1 myAgent() {
        return (AgentT1) super.myAgent();
    }

}
