package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="35"
public class ManagerAirport extends Manager {

    public ManagerAirport(int id, Simulation mySim, Agent myAgent) {
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

    //meta! sender="AgentModel", id="25", type="Notice"
    public void processArrivalCustomerT1(MessageForm message) {
        message.setCode(Mc.arrivalCustomer);
        message.setAddressee(mySim().findAgent(Id.agentT1));
        notice(message);
    }

    private void processArrivalCustomerT2(MessageForm message) {
        message.setCode(Mc.arrivalCustomer);
        message.setAddressee(mySim().findAgent(Id.agentT2));
        notice(message);
    }

    private void processArrivalCustomerRental(MessageForm message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //meta! sender="AgentRental", id="46", type="Response"
    public void processFinishProcessMovingMinibusToRental(MessageForm message) {
    }

    //meta! sender="AgentT3", id="45", type="Response"
    public void processFinishProcessMovingMinibusToT3(MessageForm message) {
    }

    //meta! sender="AgentT2", id="43", type="Response"
    public void processFinishProcessMovingMinibusToT2(MessageForm message) {

    }

    //meta! sender="AgentT3", id="32", type="Notice"
    public void processDepartureCustomerAgentT3(MessageForm message) {
    }

    //meta! sender="AgentRental", id="33", type="Notice"
    public void processDepartureCustomerAgentRental(MessageForm message) {
    }

    //meta! sender="AgentT1", id="44", type="Response"
    public void processFinishProcessMovingMinibusToT1(MessageForm message) {
        message.setCode(Mc.arrivalMinibus);
        message.setAddressee(mySim().findAgent(Id.agentT1));
        notice(message);
    }

    private void processmovMinibusToT1(MessageForm message) {
        message.setAddressee(myAgent().findAssistant(Id.processMovingMinibusToT1));
        startContinualAssistant(message);
    }

    private void processmovMinibusToT2(MessageForm message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void processmovMinibusToRental(MessageForm message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void processmovMinibusToT3(MessageForm message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

            case Mc.departureCustomer:
                switch (message.sender().id()) {
                    case Id.agentT3:
                        processDepartureCustomerAgentT3(message);
                        break;

                    case Id.agentRental:
                        processDepartureCustomerAgentRental(message);
                        break;
                }
                break;

            case Mc.finish:
                switch (message.sender().id()) {
                    case Id.processMovingMinibusToT2:
                        processFinishProcessMovingMinibusToT2(message);
                        break;

                    case Id.processMovingMinibusToT3:
                        processFinishProcessMovingMinibusToT3(message);
                        break;

                    case Id.processMovingMinibusToRental:
                        processFinishProcessMovingMinibusToRental(message);
                        break;

                    case Id.processMovingMinibusToT1:
                        processFinishProcessMovingMinibusToT1(message);
                        break;
                }
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

            case Mc.movMinibusToT1:
                processmovMinibusToT1(message);
                break;

            case Mc.movMinibusToT2:
                processmovMinibusToT2(message);
                break;

            case Mc.movMinibusToT3:
                processmovMinibusToT3(message);
                break;

            case Mc.movMinibusToRental:
                processmovMinibusToRental(message);
                break;

            default:
                processDefault(message);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentAirport myAgent() {
        return (AgentAirport) super.myAgent();
    }
}
