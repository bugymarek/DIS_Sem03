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
        message.setCode(Mc.arrivalCustomer);
        message.setAddressee(mySim().findAgent(Id.agentRental));
        notice(message);
    }

    //meta! sender="AgentRental", id="46", type="Response"
    public void processFinishProcessMovingMinibusToRental(MessageForm message) {
        message.setCode(Mc.serveArrivalMinibus);
        message.setAddressee(mySim().findAgent(Id.agentRental));
        request(message);
    }

    //meta! sender="AgentT3", id="45", type="Response"
    public void processFinishProcessMovingMinibusToT3(MessageForm message) {
        message.setCode(Mc.serveArrivalMinibus);
        message.setAddressee(mySim().findAgent(Id.agentT3));
        request(message);
    }

    //meta! sender="AgentT2", id="43", type="Response"
    public void processFinishProcessMovingMinibusToT2(MessageForm message) {
        message.setCode(Mc.serveArrivalMinibus);
        message.setAddressee(mySim().findAgent(Id.agentT2));
        request(message);
    }

    //meta! sender="AgentT3", id="32", type="Notice"
    public void processDepartureCustomerAgentT3(MessageForm message) {
        message.setAddressee(mySim().findAgent(Id.agentModel));
        notice(message);
    }

    //meta! sender="AgentRental", id="33", type="Notice"
    public void processDepartureCustomerAgentRental(MessageForm message) {
        message.setAddressee(mySim().findAgent(Id.agentModel));
        notice(message);
        //System.out.println("Zakaznik Odchadza ");
    }

    //meta! sender="AgentT1", id="44", type="Response"
    public void processFinishProcessMovingMinibusToT1(MessageForm message) {
        message.setCode(Mc.serveArrivalMinibus);
        message.setAddressee(mySim().findAgent(Id.agentT1));
        request(message);
    }

    private void processMovMinibusToT1(MessageForm message) {
        myMessage(message).getMinibus().setPosition("Cestujem z rental do T1");
        myMessage(message).getMinibus().addKm(Config.LengthRentalToT1);
        message.setAddressee(myAgent().findAssistant(Id.processMovingMinibusToT1));
        startContinualAssistant(message);
    }

    private void processMovMinibusFromT3ToT1(MessageForm message) {
        myMessage(message).getMinibus().setPosition("Cestujem z T3 do T1");
        myMessage(message).getMinibus().addKm(Config.LengthT3ToT1);
        message.setAddressee(myAgent().findAssistant(Id.processMovingMinibusFromT3ToT1));
        startContinualAssistant(message);
    }

    private void processMovMinibusToT2(MessageForm message) {
        myMessage(message).getMinibus().setPosition("Cestujem z T1 do T2");
        myMessage(message).getMinibus().addKm(Config.LengthT1ToT2);
        message.setAddressee(myAgent().findAssistant(Id.processMovingMinibusToT2));
        startContinualAssistant(message);
    }

    private void processMovMinibusToRental(MessageForm message) {
        myMessage(message).getMinibus().setPosition("Cestujem z T2 do rental");
        myMessage(message).getMinibus().addKm(Config.LengthT2ToRental);
        message.setAddressee(myAgent().findAssistant(Id.processMovingMinibusToRental));
        startContinualAssistant(message);
    }

    private void processMovMinibusToT3orT1(MessageForm message) {
        if (((MyMessage) message).getMinibus().isEmpty()) {
            processMovMinibusToT1(message);
        } else {
            myMessage(message).getMinibus().setPosition("Cestujem z rental do T3");
            myMessage(message).getMinibus().addKm(Config.LengthRentalToT3);
            message.setAddressee(myAgent().findAssistant(Id.processMovingMinibusToT3));
            startContinualAssistant(message);
        }
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        switch (message.code()) {
        }
    }

    public void processLoadCustomerAgentRental(MessageForm message) {
    }

    //meta! sender="AgentBoardingCustomers", id="135", type="Response"
    public void processLoadCustomerAgentBoardingCustomers(MessageForm message) {
    }

    //meta! sender="AgentT1", id="132", type="Response"
    public void processLoadCustomerAgentT1(MessageForm message) {
    }

    //meta! sender="AgentT2", id="129", type="Request"
    public void processLoadCustomerAgentT2(MessageForm message) {
    }

    //meta! sender="AgentBoardingCustomers", id="136", type="Response"
    public void processUnloadCustomerAgentBoardingCustomers(MessageForm message) {
    }

    //meta! sender="AgentRental", id="134", type="Response"
    public void processUnloadCustomerAgentRental(MessageForm message) {
    }

    //meta! sender="AgentT3", id="133", type="Request"
    public void processUnloadCustomerAgentT3(MessageForm message) {
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    public void init() {
    }

    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {

            case Mc.unloadCustomer:
                switch (message.sender().id()) {
                    case Id.agentBoardingCustomers:
                        processUnloadCustomerAgentBoardingCustomers(message);
                        break;

                    case Id.agentRental:
                        processUnloadCustomerAgentRental(message);
                        break;

                    case Id.agentT3:
                        processUnloadCustomerAgentT3(message);
                        break;
                }
                break;

            case Mc.loadCustomer:
                switch (message.sender().id()) {
                    case Id.agentRental:
                        processLoadCustomerAgentRental(message);
                        break;

                    case Id.agentBoardingCustomers:
                        processLoadCustomerAgentBoardingCustomers(message);
                        break;

                    case Id.agentT1:
                        processLoadCustomerAgentT1(message);
                        break;

                    case Id.agentT2:
                        processLoadCustomerAgentT2(message);
                        break;
                }
                break;

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

                    case Id.processMovingMinibusFromT3ToT1:
                        processFinishProcessMovingMinibusToT1(message);
                        break;
                }
                break;

            case Mc.minibusReadyForMove:
                switch (message.sender().id()) {
                    case Id.agentModel:
                        processMovMinibusFromT3ToT1(message);
                        break;

                    case Id.agentT1:
                        processMovMinibusToT2(message);
                        break;

                    case Id.agentT2:
                        processMovMinibusToRental(message);
                        break;

                    case Id.agentT3:
                        processMovMinibusFromT3ToT1(message);
                        break;

                    case Id.agentRental:
                        processMovMinibusToT3orT1(message);
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

    private MyMessage myMessage(MessageForm message) {
        return (MyMessage) message;
    }
}
