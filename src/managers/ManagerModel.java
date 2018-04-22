package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="1"
public class ManagerModel extends Manager {

    public ManagerModel(int id, Simulation mySim, Agent myAgent) {
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

    //meta! sender="AgentAirport", id="41", type="Notice"
    public void processDepartureCustomer(MessageForm message) {
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
            case Mc.init:
                message.setAddressee(mySim().findAgent(Id.agentEnvironment));
                notice(message);
                
                MyMessage msg = new MyMessage((MyMessage) message);
                msg.setAddressee(mySim().findAgent(Id.agentAirport));
                notice(msg);
                break;
            case Mc.arrivalCustomerT1:
                processArrivalCustomerT1(message);
                break;
            case Mc.arrivalCustomerT2:
                processArrivalCustomerT2(message);
                break;

            case Mc.arrivalCustomerRental:
                processArrivalCustomerRental(message);
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
    public AgentModel myAgent() {
        return (AgentModel) super.myAgent();
    }

    private void processArrivalCustomerT1(MessageForm message) {
        message.setAddressee(mySim().findAgent(Id.agentAirport));
        notice(message);
        System.out.println("AgentModel Arrival T1 : " + ((MyMessage) message).getCustomer().getArrivalTimeToSystem());
    }

    private void processArrivalCustomerT2(MessageForm message) {
        message.setAddressee(mySim().findAgent(Id.agentAirport));
        notice(message);
        System.out.println("AgentModel Arrival T2: " + ((MyMessage) message).getCustomer().getArrivalTimeToSystem());
    }

    private void processArrivalCustomerRental(MessageForm message) {
        System.out.println("AgentModel Arrival rental: " + ((MyMessage) message).getCustomer().getArrivalTimeToSystem());
    }

}
